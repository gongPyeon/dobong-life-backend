package dobong.life.dto;

import dobong.life.enums.Role;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserSignUpDto {

    @NotNull(message = "이메일은 필수입니다")
    private String email;

    @NotNull(message = "비밀번호는 필수입니다")
    private String password;

    @NotNull(message = "이름은 필수입니다")
    private String name;
    private Role role = Role.ROLE_USER;
}

