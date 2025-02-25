package dobong.life.global.auth.controller.request;

import dobong.life.global.auth.enums.Role;
import dobong.life.global.util.annotation.Password;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@AllArgsConstructor
@Builder
public class UserSignUpDto {

    @Size(min = 1, max = 20)
    @NotBlank
    private String Id;

    @Password
    @Size(min = 8, max = 30)
    @NotBlank
    private String pwd;

    private String pwdCheck;

    @Size(min = 1, max = 30)
    @NotBlank
    private String nickName;
    private Role role = Role.USER_REGULAR;
}

