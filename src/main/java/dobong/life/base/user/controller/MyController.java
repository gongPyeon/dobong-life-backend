package dobong.life.base.user.controller;

import dobong.life.base.user.controller.response.*;
import dobong.life.base.user.service.MyPageService;
import dobong.life.domain.user.controller.response.MyPageReviewResDto;
import dobong.life.global.auth.service.principal.UserPrincipal;
import dobong.life.global.util.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequiredArgsConstructor
@RequestMapping("dobong/my")
@Slf4j
public class MyController {

    private final MyPageService myPageService;
    @GetMapping
    public BaseResponse<MyPageResDTO> viewMyPage(@AuthenticationPrincipal UserPrincipal userPrincipal){
        Long userId = userPrincipal.getId();
        MyPageResDTO resDTO = myPageService.getMyPage(userId);
        return new BaseResponse<>(resDTO);
    }

    @GetMapping("/review")
    public BaseResponse<MyPageReviewResDTO> viewMyReview(@AuthenticationPrincipal UserPrincipal userPrincipal){
        Long userId = userPrincipal.getId();
        MyPageReviewResDTO resDTO = myPageService.getMyReview(userId);
        return new BaseResponse<>(resDTO);
    }

    @GetMapping("/contactUs")
    public BaseResponse<ContactUsDTO> contactUs(){
        ContactUsDTO resDTO = myPageService.getContactUsDTO();
        return new BaseResponse<>(resDTO);
    }

    @GetMapping("/termsOfService")
    public BaseResponse<TermsOfServiceDTO> TermsOfService(){
        TermsOfServiceDTO resDTO = myPageService.getTermsOfServiceDTO();
        return new BaseResponse<>(resDTO);
    }

    @GetMapping("/privacyPolicy")
    public BaseResponse<PrivacyPolicyDTO> privacyPolicy(){
        PrivacyPolicyDTO resDTO = myPageService.getPrivacyPolicyDTO();
        return new BaseResponse<>(resDTO);
    }

    @GetMapping("/openSourceLicenses")
    public BaseResponse<OpenSourceLicensesDTO> openSourceLicenses(){
        OpenSourceLicensesDTO resDTO = myPageService.getOpenSourceLicensesDTO();
        return new BaseResponse<>(resDTO);
    }
}
