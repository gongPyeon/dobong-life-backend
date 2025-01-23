package dobong.life.controller;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequiredArgsConstructor
@RequestMapping("/dobong")
@Slf4j
public class StoreController {

    private final BaseResponseService baseResponseService;
    private final StoreService storeService;
    private final JwtService jwtService;

    @GetMapping("/food")
    public BaseResponse<Object> viewStoreList(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestParam Long categoryId){
        String email = userPrincipal.getEmail();
        StoreListResponseDto storeListResponseDto = storeService.getStoreList(categoryId, email);
        return baseResponseService.getSuccessResponse(storeListResponseDto);
    }

    @GetMapping("/food/search")
    public BaseResponse<Object> searchStoreList(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                @RequestParam Long categoryId, @RequestParam String query){
        String email = userPrincipal.getEmail();
        StoreListResponseDto storeListResponseDto = storeService.getStoreList(categoryId, email, query);
        return baseResponseService.getSuccessResponse(storeListResponseDto);
    }

}
