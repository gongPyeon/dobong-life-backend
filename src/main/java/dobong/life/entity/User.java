package dobong.life.entity;

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
    private String oauth2Id;

    @Enumerated(EnumType.STRING)
    @Column(name = "social_type", nullable = true)
    private SocialType socialType;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Password
    private String password;

    @Column(nullable = true)
    private int reviewCount;

    public User update(OAuth2UserInfo oAuth2UserInfo) {
        this.name = oAuth2UserInfo.getName();
        this.oauth2Id = oAuth2UserInfo.getOAuth2Id();

        return this;
    }

    public void passwordEncode(PasswordEncoder passwordEncoder){
        this.password = passwordEncoder.encode(this.password);
    }

    public static User create(Long id) { // test용
        return User.builder()
                .id(id)
                .email("test@naver.com")
                .name("test")
                .oauth2Id("oauth2Id")
                .password("Oauth2!")
                .socialType(null)
                .reviewCount(0)
                .role(Role.ROLE_USER).build();
    }
}
