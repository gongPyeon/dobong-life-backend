package dobong.life.base.store.controller;

import dobong.life.base.store.controller.response.StoresByIdResDTO;
import dobong.life.base.store.controller.response.StoresByQueryResDTO;
import dobong.life.base.store.controller.response.StoresResDTO;
import dobong.life.base.store.service.StoreService;
import dobong.life.domain.store.controller.response.StoresResDto;
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
@RequestMapping("/dobong")
@Slf4j
public class StoreController {

    private final StoreService storeService;

    @GetMapping("/home")
    public BaseResponse<StoresResDTO> viewStoreList(@AuthenticationPrincipal UserPrincipal userPrincipal){
        Long userId = userPrincipal.getId();
        StoresResDTO resDTO = storeService.getStoreList(userId);
        return new BaseResponse<>(resDTO);
    }

    @GetMapping("/{categoryId}")
    public BaseResponse<StoresByIdResDTO> viewStoreListByCategory(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable Long categoryId){
        Long userId = userPrincipal.getId();
        StoresByIdResDTO resDTO = storeService.getStoreListByCategory(userId, categoryId);
        return new BaseResponse<>(resDTO);
    }

    @GetMapping("/search")
    public BaseResponse<StoresByQueryResDTO> searchStoreList(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                             @RequestParam String query, @RequestParam List<String> filter){
        Long userId = userPrincipal.getId();
        StoresByQueryResDTO resDTO = storeService.searchStoreList(userId, query, filter);
        return new BaseResponse<>(resDTO);
    }
}
