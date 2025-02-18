package dobong.life.service.principal;

import dobong.life.dto.RegisterResponse;
import dobong.life.entity.User;
import dobong.life.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Slf4j
public class UserPrincipal implements OAuth2User, UserDetails {

    private final RegisterResponse registerResponse;
    private final Collection<? extends GrantedAuthority> authorities;
    private final Map<String, Object> attributes;

    // 소셜 로그인
    public UserPrincipal(RegisterResponse registerResponse, Map<String, Object> attributes) {
        this.registerResponse = registerResponse;
        this.attributes = attributes;
        this.authorities = Collections.singletonList(
                new SimpleGrantedAuthority(registerResponse.role().getDescription())
        );
    }

    // 일반 로그인
    public UserPrincipal(RegisterResponse registerResponse) {
        this.registerResponse = registerResponse;
        this.attributes = null;
        this.authorities = Collections.singletonList(
                new SimpleGrantedAuthority(registerResponse.role().getDescription())
        );
    }

    // TODO: 각각 null 일 경우 예외처리를 해놓을지 고민
    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public Long getId(){
        return registerResponse.userId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return registerResponse.password();
    }

    @Override
    public String getUsername() {
        return registerResponse.name();
    }


    @Override
    public String getName() {
        return registerResponse.providerId();
    }
}
