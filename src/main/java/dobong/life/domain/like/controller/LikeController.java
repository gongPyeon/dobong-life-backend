package dobong.life.domain.like.controller;

import dobong.life.domain.like.service.LikeService;
import dobong.life.global.auth.service.principal.UserPrincipal;
import dobong.life.global.util.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequiredArgsConstructor
@RequestMapping("dobong/like")
@Slf4j
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/{storeId}")
    public BaseResponse<String> updateStoreLike(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable Long storeId){
        Long userId = userPrincipal.getId();
        String message = likeService.updateStoreLikeByUser(userId, storeId);
        return new BaseResponse<>(message);
    }

    @PostMapping("/review/{reviewId}")
    public BaseResponse<String> updateReviewLike(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable Long reviewId){
        Long userId = userPrincipal.getId();
        String message = likeService.updateReviewLikeByUser(userId, reviewId);
        return new BaseResponse<>(message);
    }
}
