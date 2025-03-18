package dobong.life.base.review.controller;

import dobong.life.base.review.controller.request.ReviewReqDTO;
import dobong.life.base.review.service.ReviewService;
import dobong.life.base.store.controller.response.StoreResDTO;
import dobong.life.global.auth.service.principal.UserPrincipal;
import dobong.life.global.util.response.BaseResponse;
import dobong.life.global.util.response.status.BaseCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequiredArgsConstructor
@RequestMapping("/dobong")
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/review/create")
    public BaseResponse<String> createMyReview(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                               @Valid @RequestBody ReviewReqDTO reviewReqDTO){
        Long userId = userPrincipal.getId();
        String message = reviewService.saveReview(reviewReqDTO, userId);
        return new BaseResponse<>(message);
    }

    @PostMapping("/review/{reviewId}/like")
    public BaseResponse<String> updateReviewLike(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                 @PathVariable Long reviewId){
        Long userId = userPrincipal.getId();
        String message = reviewService.updateReviewLikeByUser(userId, reviewId);
        return new BaseResponse<>(message);
    }

    // TODO: 본인 댓글일때만 삭제 가능하도록 설정
    @DeleteMapping("/review/{reviewId}")
    public BaseResponse<String> deleteReviewLike(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                 @PathVariable Long reviewId){
        String message = reviewService.deleteReview(reviewId);
        return new BaseResponse<>(message);
    }

}
