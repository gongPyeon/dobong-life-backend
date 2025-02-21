package dobong.life.controller;

import dobong.life.dto.UploadImageRequest;
import dobong.life.service.UserService;
import dobong.life.service.principal.UserPrincipal;
import dobong.life.util.response.BaseResponse;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import dobong.life.service.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@Controller
@ResponseBody
@RequiredArgsConstructor
@RequestMapping("dobong/image")
@Slf4j
public class ImageController {

    private final UserService userService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<String> uploadImage(@AuthenticationPrincipal UserPrincipal userPrincipal,
            @ModelAttribute @Valid UploadImageRequest uploadImageRequest){
        String message = userService.uploadImage(userPrincipal.getId(), uploadImageRequest.image());
        return new BaseResponse<>(message);
    }

    @DeleteMapping
    public BaseResponse<String> deleteImage(@AuthenticationPrincipal UserPrincipal userPrincipal){
        String message = userService.deleteImage(userPrincipal.getId());
        return new BaseResponse<>(message);
    }

}
