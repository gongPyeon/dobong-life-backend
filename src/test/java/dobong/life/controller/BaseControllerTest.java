package dobong.life.controller;

import dobong.life.entity.User;
import dobong.life.service.principal.UserPrincipal;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collections;

public abstract class BaseControllerTest {

    protected Authentication auth;
    @BeforeEach
    void setUp(){ // 이 부분은 testSecurityConfig 아니면 baseController로 빼기 (공통로직일 경우)
        UserPrincipal testUserPrincipal = UserPrincipal.create(User.create(1L)); // 왜 anyLong하면 안되는거지?
        auth = new UsernamePasswordAuthenticationToken(
                testUserPrincipal, null, Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
