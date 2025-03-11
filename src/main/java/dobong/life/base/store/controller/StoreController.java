package dobong.life.base.store.controller;

import dobong.life.base.store.controller.response.StoresResDTO;
import dobong.life.base.store.service.StoreService;
import dobong.life.domain.store.controller.response.StoresResDto;
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
public class StoreController {

    private final StoreService storeService;

    @GetMapping("/home")
    public BaseResponse<StoresResDTO> viewStoreList(@AuthenticationPrincipal UserPrincipal userPrincipal){
        Long userId = userPrincipal.getId();
        StoresResDTO storesResDTO = storeService.getStoreList(userId);
        return new BaseResponse<>(storesResDTO);
    }
}
