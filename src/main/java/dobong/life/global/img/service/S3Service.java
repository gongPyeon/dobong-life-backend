package dobong.life.global.img.service;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import dobong.life.global.img.exception.S3BadRequestException;
import dobong.life.global.util.response.status.BaseErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.UUID;

import static java.util.Objects.isNull;

@Service
@Slf4j
@RequiredArgsConstructor
public class S3Service {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 amazonS3;
    private static final String s3FolderName = "images";
    private static final List<String> FILE_EXTENSIONS = List.of(".jpg", ".jpeg", ".png", ".JPG",
            ".JPEG", ".PNG", ".webp", ".WEBP");

    // s3 이미지 업로드
    public String uploadImage(MultipartFile multipartFile){
        ObjectMetadata objectMetadata = new ObjectMetadata();

        String fileName = createFileName(multipartFile.getOriginalFilename());
        // 파일명 충돌 방지, 파일 이름 유출 방지

        objectMetadata.setContentLength(multipartFile.getSize());
        objectMetadata.setContentType(multipartFile.getContentType());

        try(InputStream inputStream = multipartFile.getInputStream()){
            // S3에 파일 업로드 요청 생성
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucket,s3FolderName + "/" + fileName,
                    inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead);

            amazonS3.putObject(putObjectRequest);
        }catch (IOException e){
            throw new S3BadRequestException(BaseErrorCode.FAIL_IMG, "[ERROR] S3에 이미지를 업로드하는데 실패했습니다");
        }
        return amazonS3.getUrl(bucket,s3FolderName + "/" + fileName).toString();
    }

    private String createFileName(String fileName) {
        return UUID.randomUUID().toString().concat(getFileExtension(fileName));
    } // 무작위로 생성된 UUID와 주어진 파일 이름의 확장자를 결합

    private String getFileExtension(String fileName){
        if(isNull(fileName) || fileName.isBlank()){
            throw new S3BadRequestException(BaseErrorCode.INVALID_IMG, "[ERROR] 잘못된 파일입니다");
        }

        String extension = fileName.substring(fileName.lastIndexOf("."));
        if(!FILE_EXTENSIONS.contains(extension)){
            throw new S3BadRequestException(BaseErrorCode.INVALID_IMG_FORMAT, "[ERROR] 잘못된 파일 형식입니다");
        }

        return extension;

    }

    public void deleteImage(String imgUrl){
        try{
            URL url = new URL(imgUrl);
            String path = url.getPath();

            String key = path;
            if (path.startsWith("/")) {
                key = path.substring(2 + bucket.length()); // 맨 앞 / + bucket 이름 + / 제거
            }
            amazonS3.deleteObject(bucket, key);
        }catch (SdkClientException e){
            throw new S3BadRequestException(BaseErrorCode.FAIL_IMG, "[ERROR] S3에 있는 이미지를 제거하는데 실패했습니다");
        } catch (MalformedURLException e) {
            throw new S3BadRequestException(BaseErrorCode.IMG_NOT_FOUND, "[ERROR] 저장되어있는 이미지가 없습니다");
        }
    }

}

// https://s3.ap-northeast-2.amazonaws.com/dobong-life-img-s3/images/edcc8f06-13b2-4696-9783-6f6d38f034b0.jpeg
