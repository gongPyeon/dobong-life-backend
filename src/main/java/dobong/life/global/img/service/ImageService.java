package dobong.life.global.img.service;

import dobong.life.base.user.User;
import dobong.life.base.user.service.query.UserQueryService;
import dobong.life.global.util.response.status.BaseCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImageService {

    private final UserQueryService userQueryService;
    private final S3Service s3Service;

    @Transactional
    public String uploadImage(Long userId, MultipartFile image) {
        User user = userQueryService.getUserById(userId);
        String imgUrl = s3Service.uploadImage(image);
        user.updateImgUrl(imgUrl);
        userQueryService.save(user);

        return BaseCode.SUCCESS_FIX_IMG.getMessage();
    }

    @Transactional
    public String deleteImage(Long userId) {
        User user = userQueryService.getUserById(userId);
        s3Service.deleteImage(user.getImgUrl());
        user.deleteImgUrl();
        userQueryService.save(user);

        return BaseCode.SUCCESS_DELETE_IMG.getMessage();
    }
}
