package dobong.life.entity;

import dobong.life.enums.Role;
import dobong.life.enums.SocialType;
import dobong.life.userInfo.OAuth2UserInfo;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Builder
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

//    private int age;
//
//    private String phone;

    private String password;

//    private LocalDateTime registrationDate;

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

}
