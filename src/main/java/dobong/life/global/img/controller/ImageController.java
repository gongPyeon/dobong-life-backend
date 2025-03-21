package dobong.life.global.img.controller;

import dobong.life.global.img.service.ImageService;
import dobong.life.global.auth.service.principal.UserPrincipal;
import dobong.life.global.img.controller.request.UploadImageRequest;
import dobong.life.global.util.response.BaseResponse;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequiredArgsConstructor
@RequestMapping("dobong/image")
@Slf4j
public class ImageController {

    private final ImageService imageService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<String> uploadImage(@AuthenticationPrincipal UserPrincipal userPrincipal,
            @ModelAttribute @Valid UploadImageRequest uploadImageRequest){
        String message = imageService.uploadImage(userPrincipal.getId(), uploadImageRequest.image());
        return new BaseResponse<>(message);
    }

    @DeleteMapping
    public BaseResponse<String> deleteImage(@AuthenticationPrincipal UserPrincipal userPrincipal){
        String message = imageService.deleteImage(userPrincipal.getId());
        return new BaseResponse<>(message);
    }

}
