package dobong.life.controller;

import dobong.life.dto.StoreItemResponseDto;
import dobong.life.dto.StoreListResponseDto;
import dobong.life.jwt.JwtService;
import dobong.life.service.StoreService;
import dobong.life.service.principal.UserPrincipal;
import dobong.life.util.response.BaseResponse;
import dobong.life.util.response.BaseResponseService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
        String email = userPrincipal.getEmail();
        StoreListResponseDto storeListResponseDto = storeService.getStoreList(categoryId, email);
        return baseResponseService.getSuccessResponse(storeListResponseDto);
    }

    @GetMapping("/search")
    public BaseResponse<Object> searchStoreList(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                @PathVariable("categoryId") Long categoryId, @RequestParam String query){
        String email = userPrincipal.getEmail();
        StoreListResponseDto storeListResponseDto = storeService.getStoreList(categoryId, email, query);
        return baseResponseService.getSuccessResponse(storeListResponseDto);
    }

    @GetMapping("/more")
    public BaseResponse<Object> searchStoreList(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                @PathVariable("categoryId") Long categoryId, @RequestParam Long tagCategoryId, @RequestParam String hashTag){
        String email = userPrincipal.getEmail();
        StoreListResponseDto storeListResponseDto = storeService.getStoreList(categoryId, email, tagCategoryId, hashTag);
        return baseResponseService.getSuccessResponse(storeListResponseDto);
    }

    @GetMapping("/item")
    public BaseResponse<Object> searchStoreList(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                @PathVariable("categoryId") Long categoryId, @RequestParam Long storeId){
        String email = userPrincipal.getEmail();
        StoreItemResponseDto storeItemResponseDto = storeService.getStore(categoryId, email, storeId);
        return baseResponseService.getSuccessResponse(storeItemResponseDto);
    }

}
