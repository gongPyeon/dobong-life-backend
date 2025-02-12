package dobong.life.config;

import dobong.life.entity.User;
import dobong.life.service.principal.UserPrincipal;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

//@Import(TestSecurityConfig.class)
public class TestSetUpConfig {
//    @BeforeEach
//    public void setUp() {
//        User user = User.builder()
//                .id(1L)
//                .email("test@naver.com")
//                .name("testName")
//                .password("Abcdefg123!")
//                .build();
//
//        UserPrincipal userPrincipal = UserPrincipal.create(user);
//        Authentication authentication = new UsernamePasswordAuthenticationToken(userPrincipal, "", userPrincipal.getAuthorities());
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//    }
}
