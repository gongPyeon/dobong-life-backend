package dobong.life.domain.user.controller;

import dobong.life.domain.user.controller.response.MyPageResDto;
import dobong.life.domain.user.controller.response.MyPageReviewResDto;
import dobong.life.domain.store.dto.StoreBasicInfo;
import dobong.life.domain.user.service.MyPageService;
import dobong.life.global.auth.service.principal.UserPrincipal;
import dobong.life.global.util.response.BaseResponse;
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
    private final MyPageService myPageService;

    @GetMapping
    public BaseResponse<MyPageResDto> viewMyPage(@AuthenticationPrincipal UserPrincipal userPrincipal){
        Long userId = userPrincipal.getId();
        MyPageResDto myPageResDto = myPageService.getMyPage(userId);
        return new BaseResponse<>(myPageResDto);
    }

    @GetMapping("/review")
    public BaseResponse<MyPageReviewResDto> viewMyReview(@AuthenticationPrincipal UserPrincipal userPrincipal){
        Long userId = userPrincipal.getId();
        MyPageReviewResDto myPageResDto = myPageService.getMyReview(userId);
        return new BaseResponse<>(myPageResDto);
    }

    @GetMapping("/review/like")
    public BaseResponse<MyPageReviewResDto> viewReviewInLike(@AuthenticationPrincipal UserPrincipal userPrincipal){
        Long userId = userPrincipal.getId();
        MyPageReviewResDto myPageResDto = myPageService.getMyReviewLike(userId);
        return new BaseResponse<>(myPageResDto);
    }

    @GetMapping("/{categoryId}/like")
    public BaseResponse<List<StoreBasicInfo>> viewStoreInLike(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable Long categoryId){
        Long userId = userPrincipal.getId();
        List<StoreBasicInfo> storesResDto = myPageService.getMyLike(categoryId, userId);
        return new BaseResponse<>(storesResDto);
    }
}
