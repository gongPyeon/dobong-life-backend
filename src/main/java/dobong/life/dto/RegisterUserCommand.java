package dobong.life.dto;

import dobong.life.enums.Role;
import dobong.life.enums.SocialType;

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
