package dobong.life.dto;

import dobong.life.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserSignUpDto {

    @NotBlank(message = "[ERROR] 이메일은 필수입니다")
    @Email(message = "유효한 이메일 형식이 아닙니다")
    private String email;

    @NotBlank(message = "[ERROR] 비밀번호는 필수입니다")
    private String password;

    @NotBlank(message = "[ERROR] 이름은 필수입니다")
    private String name;
    private Role role = Role.ROLE_USER;
}

