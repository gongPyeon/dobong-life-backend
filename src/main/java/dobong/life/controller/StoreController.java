package dobong.life.controller;

import dobong.life.dto.StoreItemResDto;
import dobong.life.dto.StoresFilterResDto;
import dobong.life.dto.StoresResDto;
import dobong.life.service.StoreService;
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
@RequestMapping("/dobong/{categoryId}")
@Slf4j
public class StoreController {

    private final BaseResponseService baseResponseService;
    private final StoreService storeService;

    @GetMapping
    public BaseResponse<Object> viewStoreList(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable("categoryId") Long categoryId){
        Long userId = userPrincipal.getId();
        StoresResDto storesResDto = storeService.getStoreList(categoryId, userId);
        return baseResponseService.getSuccessResponse(storesResDto);
    }

    @GetMapping("/search")
    public BaseResponse<Object> searchStoreList(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                @PathVariable("categoryId") Long categoryId, @RequestParam String query){
        Long userId = userPrincipal.getId();
        StoresResDto storesResDto = storeService.getStoreListByQuery(categoryId, userId, query);
        return baseResponseService.getSuccessResponse(storesResDto);
    }

    @GetMapping("/filter")
    public BaseResponse<Object> filterStoreList(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                @PathVariable("categoryId") Long categoryId,
                                                @RequestParam List<String> categoryName, @RequestParam List<Long> subTagId){
        Long userId = userPrincipal.getId();
        StoresFilterResDto storesFilterResDto = storeService.getStoreListByFilter(categoryId, userId, categoryName, subTagId);
        return baseResponseService.getSuccessResponse(storesFilterResDto);
    }

    @GetMapping("/more")
    public BaseResponse<Object> viewStoreListMore(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                @PathVariable("categoryId") Long categoryId, @RequestParam Long tagId, @RequestParam Long subTagId){
        Long userId = userPrincipal.getId();
        StoresResDto storesResDto = storeService.getStoreListAll(categoryId, userId, tagId, subTagId);
        return baseResponseService.getSuccessResponse(storesResDto);
    }

    @GetMapping("/item")
    public BaseResponse<Object> viewStore(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                @PathVariable("categoryId") Long categoryId, @RequestParam Long storeId) {
        Long userId = userPrincipal.getId();
        StoreItemResDto storeItemResDto = storeService.getStore(categoryId, userId, storeId);
        return baseResponseService.getSuccessResponse(storeItemResDto);
    }

}
