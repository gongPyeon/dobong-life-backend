package dobong.life.global.auth.dto;

import dobong.life.global.auth.enums.Role;
import dobong.life.global.auth.enums.SocialType;
import lombok.Builder;

@Builder
public record RegisterUserCommand(
        String name,
        String email,
        String providerId,
        SocialType providerType,
        Role role
) {
    public static RegisterUserCommand of(String name, String email, String providerId,
                                         SocialType providerType, Role role) {
        return new RegisterUserCommand(name, email, providerId, providerType, role);
    }
}
