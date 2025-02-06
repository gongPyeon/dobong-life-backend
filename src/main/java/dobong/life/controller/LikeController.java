package dobong.life.controller;

import dobong.life.service.LikeService;
import dobong.life.service.principal.UserPrincipal;
import dobong.life.util.DEFINE;
import dobong.life.util.ValidParameter;
import dobong.life.util.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequiredArgsConstructor
@RequestMapping("dobong/like")
@Slf4j
public class LikeController {

    private final LikeService likeService;

    @GetMapping("/{storeId}")
    public BaseResponse<String> updateStoreLike(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                @ValidParameter @PathVariable Long storeId){
        Long userId = userPrincipal.getId();
        likeService.updateStoreLikeByUser(userId, storeId);
        return new BaseResponse<>(DEFINE.LIKE_OK);
    }

    @GetMapping("/review/{reviewId}")
    public BaseResponse<String> updateReviewLike(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                 @ValidParameter @PathVariable Long reviewId){
        Long userId = userPrincipal.getId();
        likeService.updateReviewLikeByUser(userId, reviewId);
        return new BaseResponse<>(DEFINE.LIKE_OK);
    }
}
