package dobong.life.global.auth.service;

import dobong.life.global.auth.dto.RegisterResponse;
import dobong.life.global.auth.service.principal.UserPrincipal;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailService implements UserDetailsService {

    private final UserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("loadUserByUsername의 email = {}", email);
        RegisterResponse registerResponse = userService.getRegisterUser(email);
        return new UserPrincipal(registerResponse);
    }
}
