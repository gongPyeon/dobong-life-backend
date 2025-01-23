package dobong.life.controller;

import dobong.life.dto.StoreListResponseDto;
import dobong.life.jwt.JwtService;
import dobong.life.service.StoreService;
import dobong.life.util.response.BaseResponse;
import dobong.life.util.response.BaseResponseService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public BaseResponse<Object> viewStoreList(HttpServletRequest request, @RequestParam Long categoryId){
        String email = jwtService.parseClaims(jwtService.resolveToken(request)).getSubject();
        StoreListResponseDto StoreListResponseDto = storeService.getStoreList(categoryId, email);
        return baseResponseService.getSuccessResponse(StoreListResponseDto);
    }

}
