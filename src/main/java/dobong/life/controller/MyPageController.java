package dobong.life.controller;

import dobong.life.dto.MyPageResDto;
import dobong.life.dto.MyPageReviewResDto;
import dobong.life.dto.info.StoreBasicInfo;
import dobong.life.service.MyPageService;
import dobong.life.service.principal.UserPrincipal;
import dobong.life.util.response.BaseResponse;
import dobong.life.util.response.BaseResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
@RequiredArgsConstructor
@RequestMapping("dobong/my")
@Slf4j
public class MyPageController {

    private final BaseResponseService baseResponseService;
    private final MyPageService myPageService;

    @GetMapping
    public BaseResponse<Object> viewMyPage(@AuthenticationPrincipal UserPrincipal userPrincipal){
        Long userId = userPrincipal.getId();
        MyPageResDto myPageResDto = myPageService.getMyPage(userId);
        return baseResponseService.getSuccessResponse(myPageResDto);
    }

    @GetMapping("/review")
    public BaseResponse<Object> viewMyReview(@AuthenticationPrincipal UserPrincipal userPrincipal){
        Long userId = userPrincipal.getId();
        MyPageReviewResDto myPageResDto = myPageService.getMyReview(userId);
        return baseResponseService.getSuccessResponse(myPageResDto);
    }

    @GetMapping("/review/like")
    public BaseResponse<Object> viewReviewInLike(@AuthenticationPrincipal UserPrincipal userPrincipal){
        Long userId = userPrincipal.getId();
        MyPageReviewResDto myPageResDto = myPageService.getMyReviewLike(userId);
        return baseResponseService.getSuccessResponse(myPageResDto);
    }

    @GetMapping("/{categoryId}/like")
    public BaseResponse<Object> viewStoreInLike(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable("categoryId") Long categoryId){
        Long userId = userPrincipal.getId();
        List<StoreBasicInfo> storesResDto = myPageService.getMyLike(categoryId, userId);
        return baseResponseService.getSuccessResponse(storesResDto);
    }
}
