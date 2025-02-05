package dobong.life.controller;

import dobong.life.dto.ReviewResDto;
import dobong.life.dto.info.MyPageReviewInfo;
import dobong.life.service.ReviewService;
import dobong.life.service.principal.UserPrincipal;
import dobong.life.util.response.BaseResponse;
import dobong.life.util.response.BaseResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequiredArgsConstructor
@RequestMapping("dobong")
@Slf4j
public class ReviewController {
    private final BaseResponseService baseResponseService;
    private final ReviewService reviewService;
    @PostMapping("/review")
    public BaseResponse<Object> createMyReview(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody MyPageReviewInfo myPageReviewInfo){
        Long userId = userPrincipal.getId();
        reviewService.saveReview(myPageReviewInfo, userId);
        return baseResponseService.getSuccessResponse();
    }

    @GetMapping("/more/review")
    public BaseResponse<Object> viewStoreReview(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                @PathVariable("categoryId") Long categoryId, @RequestParam Long storeId){
        Long userId = userPrincipal.getId();
        ReviewResDto reviewResDto = reviewService.getStoreReview(categoryId, userId, storeId);
        return baseResponseService.getSuccessResponse(reviewResDto);
    }

}
