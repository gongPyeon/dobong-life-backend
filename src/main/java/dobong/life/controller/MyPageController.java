package dobong.life.controller;

import dobong.life.dto.MyPageResDto;
import dobong.life.dto.MyPageReviewResDto;
import dobong.life.dto.info.StoreBasicInfo;
import dobong.life.service.MyPageService;
import dobong.life.service.principal.UserPrincipal;
import dobong.life.util.response.BaseResponse;
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
        // Long userId = userPrincipal.getId();
        MyPageResDto myPageResDto = myPageService.getMyPage(userPrincipal);
        return new BaseResponse<>(myPageResDto);
    }

    @GetMapping("/review")
    public BaseResponse<MyPageReviewResDto> viewMyReview(@AuthenticationPrincipal UserPrincipal userPrincipal){
        // Long userId = userPrincipal.getId();
        MyPageReviewResDto myPageResDto = myPageService.getMyReview(userPrincipal);
        return new BaseResponse<>(myPageResDto);
    }

    @GetMapping("/review/like")
    public BaseResponse<MyPageReviewResDto> viewReviewInLike(@AuthenticationPrincipal UserPrincipal userPrincipal){
        // Long userId = userPrincipal.getId();
        MyPageReviewResDto myPageResDto = myPageService.getMyReviewLike(userPrincipal);
        return new BaseResponse<>(myPageResDto);
    }

    @GetMapping("/{categoryId}/like")
    public BaseResponse<List<StoreBasicInfo>> viewStoreInLike(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable Long categoryId){
        //Long userId = userPrincipal.getId();
        List<StoreBasicInfo> storesResDto = myPageService.getMyLike(categoryId, userPrincipal);
        return new BaseResponse<>(storesResDto);
    }
}
