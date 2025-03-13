package dobong.life.base.review.controller;

import dobong.life.base.review.controller.request.ReviewReqDTO;
import dobong.life.base.review.service.ReviewService;
import dobong.life.domain.review.controller.request.MyPageReviewInfo;
import dobong.life.global.auth.service.principal.UserPrincipal;
import dobong.life.global.util.response.BaseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dobong")
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/review")
    public BaseResponse<String> createMyReview(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                               @Valid @RequestBody ReviewReqDTO reviewReqDTO){
        Long userId = userPrincipal.getId();
        String message = reviewService.saveReview(reviewReqDTO, userId);
        return new BaseResponse<>(message);
    }

}
