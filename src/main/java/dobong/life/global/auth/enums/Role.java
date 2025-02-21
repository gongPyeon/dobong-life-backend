package dobong.life.global.auth.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    ROLE_GUEST("GUEST"), ROLE_USER("USER");

    private final String description;
}
