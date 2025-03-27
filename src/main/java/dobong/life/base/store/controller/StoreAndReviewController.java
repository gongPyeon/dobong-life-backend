package dobong.life.base.store.controller;

import dobong.life.base.store.controller.response.StoreResDTO;
import dobong.life.base.store.service.StoreAndReviewService;
import dobong.life.global.auth.service.principal.UserPrincipal;
import dobong.life.global.util.response.BaseErrorResponse;
import dobong.life.global.util.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequiredArgsConstructor
@RequestMapping("/dobong")
@Slf4j
@SecurityRequirement(name = "BearerAuth")
public class StoreAndReviewController {

    private final StoreAndReviewService storeAndReviewService;

    @Operation(summary = "홈 상점 목록 조회", description = "홈 화면 상점 리스트를 조회한다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "401", description = "인증이 필요해요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "접근 권한이 없어요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "카테고리를 찾을 수 없어요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "도메인을 찾을 수 없어요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "키워드(중간테이블)를 찾을 수 없어요", //
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "리뷰를 찾을 수 없어요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류에요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class)))
    })
    @GetMapping("/item/{storeId}")
    public BaseResponse<StoreResDTO> viewStore(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable Long storeId){
        Long userId = userPrincipal.getId();
        StoreResDTO resDTO = storeAndReviewService.getStore(userId, storeId);
        return new BaseResponse<>(resDTO);
    }
}
