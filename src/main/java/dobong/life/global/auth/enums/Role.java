package dobong.life.global.auth.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    ADMIN("ADMIN"),
    USER_REGULAR("USER_REGULAR"),
    USER_OAUTH2("USER_OAUTH2");

    private final String description;
}
