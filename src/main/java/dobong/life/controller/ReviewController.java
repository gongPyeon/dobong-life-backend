package dobong.life.controller;

import dobong.life.dto.ReviewResDto;
import dobong.life.dto.info.MyPageReviewInfo;
import dobong.life.service.ReviewService;
import dobong.life.service.principal.UserPrincipal;
import dobong.life.util.DEFINE;
import dobong.life.util.response.BaseResponse;
import jakarta.validation.Valid;
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
    private final ReviewService reviewService;
    @PostMapping("/review")
    public BaseResponse<String> createMyReview(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                               @Valid @RequestBody MyPageReviewInfo myPageReviewInfo){
        Long userId = userPrincipal.getId();
        String message = reviewService.saveReview(myPageReviewInfo, userId);
        return new BaseResponse<>(message);
    }

    @GetMapping("/reviews/{categoryId}/{storeId}")
    public BaseResponse<ReviewResDto> viewStoreReview(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable Long categoryId, @PathVariable Long storeId){
        Long userId = userPrincipal.getId();
        ReviewResDto reviewResDto = reviewService.getStoreReview(categoryId, storeId, userId);
        return new BaseResponse<>(reviewResDto);
    }

}
