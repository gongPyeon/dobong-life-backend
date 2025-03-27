package dobong.life.base.user.controller;

import dobong.life.base.user.controller.response.*;
import dobong.life.base.user.service.MyPageService;
import dobong.life.global.auth.service.principal.UserPrincipal;
import dobong.life.global.util.response.BaseErrorResponse;
import dobong.life.global.util.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@SecurityRequirement(name = "BearerAuth")
public class MyController {

    private final MyPageService myPageService;
    @Operation(summary = "마이 페이지 조회", description = "마이페이지를 조회한다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "401", description = "인증이 필요해요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "접근 권한이 없어요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없어요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류에요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class)))
    })
    @GetMapping
    public BaseResponse<MyPageResDTO> viewMyPage(@AuthenticationPrincipal UserPrincipal userPrincipal){
        Long userId = userPrincipal.getId();
        MyPageResDTO resDTO = myPageService.getMyPage(userId);
        return new BaseResponse<>(resDTO);
    }

    @Operation(summary = "마이 페이지 나의 리뷰 조회", description = "내가 작성한 리뷰를 조회한다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "401", description = "인증이 필요해요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "접근 권한이 없어요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없어요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류에요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class)))
    })
    @GetMapping("/review")
    public BaseResponse<MyPageReviewResDTO> viewMyReview(@AuthenticationPrincipal UserPrincipal userPrincipal){
        Long userId = userPrincipal.getId();
        MyPageReviewResDTO resDTO = myPageService.getMyReview(userId);
        return new BaseResponse<>(resDTO);
    }

    @Operation(summary = "마이 페이지 나의 리뷰 조회", description = "내가 작성한 리뷰를 조회한다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류에요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class)))
    })
    @GetMapping("info/contactUs")
    public BaseResponse<ContactUsDTO> contactUs(){
        ContactUsDTO resDTO = myPageService.getContactUsDTO();
        return new BaseResponse<>(resDTO);
    }

    @Operation(summary = "마이 페이지 나의 리뷰 조회", description = "내가 작성한 리뷰를 조회한다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류에요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class)))
    })
    @GetMapping("info/termsOfService")
    public BaseResponse<TermsOfServiceDTO> TermsOfService(){
        TermsOfServiceDTO resDTO = myPageService.getTermsOfServiceDTO();
        return new BaseResponse<>(resDTO);
    }

    @Operation(summary = "마이 페이지 나의 리뷰 조회", description = "내가 작성한 리뷰를 조회한다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류에요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class)))
    })
    @GetMapping("info/privacyPolicy")
    public BaseResponse<PrivacyPolicyDTO> privacyPolicy(){
        PrivacyPolicyDTO resDTO = myPageService.getPrivacyPolicyDTO();
        return new BaseResponse<>(resDTO);
    }

    @Operation(summary = "마이 페이지 나의 리뷰 조회", description = "내가 작성한 리뷰를 조회한다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류에요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class)))
    })
    @GetMapping("info/openSourceLicenses")
    public BaseResponse<OpenSourceLicensesDTO> openSourceLicenses(){
        OpenSourceLicensesDTO resDTO = myPageService.getOpenSourceLicensesDTO();
        return new BaseResponse<>(resDTO);
    }
}
