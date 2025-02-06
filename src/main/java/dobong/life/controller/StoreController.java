package dobong.life.controller;

import dobong.life.dto.StoreItemResDto;
import dobong.life.dto.StoresFilterResDto;
import dobong.life.dto.StoresResDto;
import dobong.life.service.StoreService;
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
@RequestMapping("/dobong/{categoryId}")
@Slf4j
public class StoreController {

    private final StoreService storeService;

    @GetMapping
    public BaseResponse<StoresResDto> viewStoreList(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable Long categoryId){
        Long userId = userPrincipal.getId();
        StoresResDto storesResDto = storeService.getStoreList(categoryId, userId);
        return new BaseResponse<>(storesResDto);
    }

    @GetMapping("/search")
    public BaseResponse<StoresResDto> searchStoreList(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable Long categoryId, @RequestParam String query){
        Long userId = userPrincipal.getId();
        StoresResDto storesResDto = storeService.getStoreListByQuery(categoryId, userId, query);
        return new BaseResponse<>(storesResDto);
    }

    @GetMapping("/filter") // id는 param으로 넘겨야하는데, 어떻게 하는게 좋을까
    public BaseResponse<StoresFilterResDto> filterStoreList(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable Long categoryId,
                                                @RequestParam List<String> categoryName, @RequestParam List<Long> subTagId){
        Long userId = userPrincipal.getId();
        StoresFilterResDto storesFilterResDto = storeService.getStoreListByFilter(categoryId, userId, categoryName, subTagId);
        return new BaseResponse<>(storesFilterResDto);
    }

    @GetMapping("/more/{tagId}/{subTagId}")
    public BaseResponse<StoresResDto> viewStoreListMore(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable Long categoryId, @PathVariable Long tagId, @PathVariable Long subTagId){
        Long userId = userPrincipal.getId();
        StoresResDto storesResDto = storeService.getStoreListAll(categoryId, userId, tagId, subTagId);
        return new BaseResponse<>(storesResDto);
    }

    @GetMapping("/item/{storeId}")
    public BaseResponse<StoreItemResDto> viewStore(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                @PathVariable Long categoryId, @PathVariable Long storeId) {
        Long userId = userPrincipal.getId();
        StoreItemResDto storeItemResDto = storeService.getStore(categoryId, userId, storeId);
        return new BaseResponse<>(storeItemResDto);
    }

}
