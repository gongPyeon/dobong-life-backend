package dobong.life.global.auth.controller.request;

import dobong.life.global.auth.enums.Role;
import dobong.life.global.util.annotation.Password;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserSignUpDto {

    @NotBlank(message = "[ERROR] 이메일은 필수입니다")
    @Email(message = "유효한 이메일 형식이 아닙니다")
    private String email;

    @NotBlank(message = "[ERROR] 비밀번호는 필수입니다")
    @Password
    private String password;

    @NotBlank(message = "[ERROR] 이름은 필수입니다")
    private String name;
    private Role role = Role.USER_REGULAR;
}

