package dobong.life.global.auth.service;

import dobong.life.global.auth.dto.RegisterResponse;
import dobong.life.global.auth.service.principal.UserPrincipal;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {

    private final UserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        RegisterResponse registerResponse = userService.getRegisterUser(email);

        return new UserPrincipal(registerResponse);
    }
}
