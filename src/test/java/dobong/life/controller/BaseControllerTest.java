package dobong.life.controller;

import org.springframework.security.core.Authentication;

public abstract class BaseControllerTest {

    protected Authentication auth;
//    @BeforeEach
//    void setUp(){ // 이 부분은 testSecurityConfig 아니면 baseController로 빼기 (공통로직일 경우)
//        UserPrincipal testUserPrincipal = UserPrincipal.create(User.create(1L)); // 왜 anyLong하면 안되는거지?
//        auth = new UsernamePasswordAuthenticationToken(
//                testUserPrincipal, null, Collections.emptyList());
//        SecurityContextHolder.getContext().setAuthentication(auth);
//    }
}
