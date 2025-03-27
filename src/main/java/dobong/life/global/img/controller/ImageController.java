package dobong.life.global.img.controller;

import dobong.life.global.img.service.ImageService;
import dobong.life.global.auth.service.principal.UserPrincipal;
import dobong.life.global.img.controller.request.UploadImageRequest;
import dobong.life.global.util.response.BaseErrorResponse;
import dobong.life.global.util.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@SecurityRequirement(name = "BearerAuth")
public class ImageController {

    private final ImageService imageService;

    @Operation(summary = "이미지 저장", description = "프로필 사진을 저장한다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "401", description = "인증이 필요해요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "접근 권한이 없어요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없어요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "이미지 처리를 실패했어요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "지원하지 않은 파일이에요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "지원하지 않은 파일형식이에요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류에요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class)))
    })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<String> uploadImage(@AuthenticationPrincipal UserPrincipal userPrincipal,
            @ModelAttribute @Valid UploadImageRequest uploadImageRequest){
        String message = imageService.uploadImage(userPrincipal.getId(), uploadImageRequest.image());
        return new BaseResponse<>(message);
    }

    @Operation(summary = "이미지 삭제", description = "프로필 사진을 삭제한다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "401", description = "인증이 필요해요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "접근 권한이 없어요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없어요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "이미지 처리를 실패했어요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "이미지를 찾을 수 없어요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류에요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class)))
    })
    @DeleteMapping
    public BaseResponse<String> deleteImage(@AuthenticationPrincipal UserPrincipal userPrincipal){
        String message = imageService.deleteImage(userPrincipal.getId());
        return new BaseResponse<>(message);
    }

}
