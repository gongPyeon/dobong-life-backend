package dobong.life.domain.image.service;

import dobong.life.domain.user.User;
import dobong.life.domain.user.service.query.UserQueryService;
import dobong.life.global.util.constant.DEFINE;
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

        return DEFINE.FIX_IMG_OK;
    }

    public String deleteImage(Long userId) {
        User user = userQueryService.getUserById(userId);
        //s3Service.deleteImage(user.getImgUrl());
        user.deleteImgUrl();

        return DEFINE.DELETE_IMG_OK;
    }
}
