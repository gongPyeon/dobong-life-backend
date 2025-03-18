package dobong.life.global.img.service;

import dobong.life.base.user.User;
import dobong.life.base.user.service.query.UserQueryService;
import dobong.life.global.util.response.status.BaseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final UserQueryService userQueryService;
    //private final S3Service s3Service;

    public String uploadImage(Long userId, MultipartFile image) {
        User user = userQueryService.getUserById(userId);
        //String imgUrl = s3Service.uploadImage(image);
        //user.updateImgUrl(imgUrl);

        return BaseCode.SUCCESS_FIX_IMG.getMessage();
    }

    public String deleteImage(Long userId) {
        User user = userQueryService.getUserById(userId);
        //s3Service.deleteImage(user.getImgUrl());
        user.deleteImgUrl();

        return BaseCode.SUCCESS_DELETE_IMG.getMessage();
    }
}
