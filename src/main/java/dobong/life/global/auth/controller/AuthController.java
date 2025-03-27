package dobong.life.global.auth.controller;

import dobong.life.global.auth.controller.request.UserSignUpDto;
import dobong.life.global.auth.jwt.JwtProvider;
import dobong.life.global.auth.service.AuthService;
import dobong.life.global.util.response.BaseErrorResponse;
import dobong.life.global.util.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@ResponseBody
@Slf4j
@RequestMapping("/auth")
@SecurityRequirement(name = "BearerAuth")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "회원가입", description = "회원가입을 진행한다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "400", description = "중복된 요청이에요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "이미 존재하는 아이디에요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "이미 존재하는 닉네임이에요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "닉네임은 알파벳, 한글, 숫자만 포함할 수 있어요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "아이디는 알파벳, 숫자만 포함할 수 있어요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class)))
    })
    @PostMapping("/sign-up")
    public BaseResponse<String> signUp(@Valid @RequestBody UserSignUpDto userSignUpDto){
        String message = authService.signUp(userSignUpDto);
        return new BaseResponse<>(message);
    }

    @Operation(summary = "아이디 중복확인", description = "아이디 중복을 확인한다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "400", description = "이미 존재하는 아이디에요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class))),
    })
    @PostMapping("/id")
    public BaseResponse<String> checkDupId(@RequestBody Map<String, String> request){
        String message = authService.checkDupId(request.get("id"));
        return new BaseResponse<>(message);
    }

    @Operation(summary = "닉네임 중복확인", description = "닉네임 중복을 확인한다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "400", description = "이미 존재하는 닉네임이에요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class))),
    })
    @PostMapping("/nickname")
    public BaseResponse<String> checkDupName(@RequestBody Map<String, String> request){
        String message = authService.checkDupNickName(request.get("nickname"));
        return new BaseResponse<>(message);
    }

    @Operation(summary = "액세스 토큰 발급", description = "리프레시 토큰으로 액세스 토큰을 발급한다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "401", description = "액세스 토큰 갱신에 실패했어요",
                    content = @Content(schema = @Schema(implementation = BaseErrorResponse.class))),
    })
    @PostMapping("/refresh")
    public BaseResponse<String> reissueToken(@RequestHeader("RefreshToken") String refreshToken){
        String accessToken = authService.reissueToken(refreshToken);

        return new BaseResponse<>(accessToken);
    }
}
