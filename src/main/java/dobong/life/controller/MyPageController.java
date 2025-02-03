package dobong.life.controller;

import dobong.life.dto.MyPageResponseDto;
import dobong.life.dto.MyPageReviewResponseDto;
import dobong.life.dto.StoreItemListResponseDto;
import dobong.life.dto.StoreItemResponseDto;
import dobong.life.service.MyPageService;
import dobong.life.service.principal.UserPrincipal;
import dobong.life.util.response.BaseResponse;
import dobong.life.util.response.BaseResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/my")
@Slf4j
public class MyPageController {
    private final BaseResponseService baseResponseService;
    private final MyPageService myPageService;

    @GetMapping
    public BaseResponse<Object> viewMyPage(@AuthenticationPrincipal UserPrincipal userPrincipal){
        String email = userPrincipal.getEmail();
        MyPageResponseDto myPageResponseDto = myPageService.getMyPage(email);
        return baseResponseService.getSuccessResponse(myPageResponseDto);
    }

    @GetMapping("/review")
    public BaseResponse<Object> viewMyReview(@AuthenticationPrincipal UserPrincipal userPrincipal){
        String email = userPrincipal.getEmail();
        MyPageReviewResponseDto myPageReviewResponseDto = myPageService.getMyReview(email);
        return baseResponseService.getSuccessResponse(myPageReviewResponseDto);
    }
//
//    @PostMapping("/review")
//    public BaseResponse<Object> createMyReview(@AuthenticationPrincipal UserPrincipal userPrincipal){
//        String email = userPrincipal.getEmail();
//        //StoreListResponseDto storeListResponseDto = myPageService.getStoreList(categoryId, email);
//        return baseResponseService.getSuccessResponse();
//    }
//
    @GetMapping("/review/like")
    public BaseResponse<Object> viewReviewInLike(@AuthenticationPrincipal UserPrincipal userPrincipal){
        String email = userPrincipal.getEmail();
        MyPageReviewResponseDto myPageReviewResponseDto = myPageService.getMyReviewLike(email);
        return baseResponseService.getSuccessResponse(myPageReviewResponseDto);
    }

    @GetMapping("/{categoryId}/like")
    public BaseResponse<Object> viewStoreInLike(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable("categoryId") Long categoryId){
        String email = userPrincipal.getEmail();
        StoreItemListResponseDto storeItemListResponseDto = myPageService.getMyLike(categoryId, email);
        return baseResponseService.getSuccessResponse(storeItemListResponseDto);
    }
}
