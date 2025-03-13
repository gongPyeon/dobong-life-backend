package dobong.life.base.store.controller;

import dobong.life.base.store.controller.response.StoreResDTO;
import dobong.life.base.store.service.StoreAndReviewService;
import dobong.life.global.auth.service.principal.UserPrincipal;
import dobong.life.global.util.response.BaseResponse;
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
@RequestMapping("/dobong")
@Slf4j
public class StoreAndReviewController {

    private final StoreAndReviewService storeAndReviewService;
    @GetMapping("/item/{storeId}")
    public BaseResponse<StoreResDTO> viewStore(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable Long storeId){
        Long userId = userPrincipal.getId();
        StoreResDTO resDTO = storeAndReviewService.getStore(userId, storeId);
        return new BaseResponse<>(resDTO);
    }
}
