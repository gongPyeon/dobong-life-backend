package dobong.life.global.auth.dto;

import dobong.life.domain.user.User;
import dobong.life.global.auth.enums.Role;

public record RegisterResponse(
    Long userId,
    String name,
    String email,
    String password,
    String providerId,
    Role role) {

    public static RegisterResponse from(User user) {
        return new RegisterResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getProviderId(),
                user.getRole()
        );
    }
}
