package dobong.life.entity;

import dobong.life.dto.RegisterUserCommand;
import dobong.life.enums.Role;
import dobong.life.enums.SocialType;
import dobong.life.userInfo.OAuth2UserInfo;
import dobong.life.util.Password;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Builder
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor //(access = AccessLevel.PROTECTED)
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String name;

    @Column(nullable = true)
    private String providerId;

    @Enumerated(EnumType.STRING)
    @Column(name = "social_type", nullable = true)
    private SocialType providerType;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Password
    private String password;

//    @Column(nullable = true)
//    private int reviewCount;

    public void passwordEncode(PasswordEncoder passwordEncoder){
        this.password = passwordEncoder.encode(this.password);
    }

    public static User create(RegisterUserCommand registerUserCommand) {
        return User.builder()
                .email(registerUserCommand.email())
                .name(registerUserCommand.name())
                .password(null)
                .providerId(registerUserCommand.providerId())
                .providerType(registerUserCommand.providerType())
                .role(registerUserCommand.role())
                .build();
    }

    public static User create(Long id) { // test용
        return User.builder()
                .id(id)
                .email("test@naver.com")
                .name("test")
                .providerId("oauth2Id")
                .password("Oauth2!")
                .providerType(null)
                .role(Role.ROLE_USER).build();
    }
}
