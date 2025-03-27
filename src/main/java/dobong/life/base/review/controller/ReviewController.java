package dobong.life.base.review.controller;

import dobong.life.base.review.controller.request.ReviewReqDTO;
import dobong.life.base.review.controller.response.ReviewsResDTO;
import dobong.life.base.review.service.ReviewService;
import dobong.life.base.store.controller.response.StoreResDTO;
import dobong.life.global.auth.service.principal.UserPrincipal;
import dobong.life.global.util.response.BaseErrorResponse;
import dobong.life.global.util.response.BaseResponse;
import dobong.life.global.util.response.status.BaseCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequiredArgsConstructor
@RequestMapping("/dobong")
@SecurityRequirement(name = "BearerAuth")
public class ReviewController {
    private final ReviewService reviewService;

    @Operation(summary = "리뷰 생성", description = "상점에 대해서 리뷰를 생성한다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "401", description = "인증이 필요해요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "접근 권한이 없어요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없어요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "도메인을 찾을 수 없어요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류에요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class)))

    })
    @PostMapping("/review")
    public BaseResponse<String> createMyReview(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                               @Valid @RequestBody ReviewReqDTO reviewReqDTO){
        Long userId = userPrincipal.getId();
        String message = reviewService.saveReview(reviewReqDTO, userId);
        return new BaseResponse<>(message);
    }

    @Operation(summary = "리뷰 좋아요", description = "해당 상점의 리뷰를 좋아요한다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "401", description = "인증이 필요해요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "접근 권한이 없어요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없어요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "리뷰를 찾을 수 없어요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류에요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class)))
    })
    @PostMapping("/review/{reviewId}/like")
    public BaseResponse<String> updateReviewLike(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                 @PathVariable Long reviewId){
        Long userId = userPrincipal.getId();
        String message = reviewService.updateReviewLikeByUser(userId, reviewId);
        return new BaseResponse<>(message);
    }

    // TODO: 본인 댓글일때만 삭제 가능하도록 설정 (댓글 조회 시 아이디와 사용자 아이디를 같이 반환하도록 설정)
    @Operation(summary = "리뷰 좋아요", description = "해당 상점의 리뷰를 좋아요한다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "401", description = "인증이 필요해요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "접근 권한이 없어요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "리뷰를 찾을 수 없어요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류에요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class)))
    })
    @DeleteMapping("/review/{reviewId}")
    public BaseResponse<String> deleteReview(@PathVariable Long reviewId){
        String message = reviewService.deleteReview(reviewId);
        return new BaseResponse<>(message);
    }
}
