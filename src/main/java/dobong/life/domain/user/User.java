package dobong.life.domain.user;

import dobong.life.global.auth.dto.RegisterUserCommand;
import dobong.life.global.auth.controller.request.UserSignUpDto;
import dobong.life.global.auth.enums.Role;
import dobong.life.global.auth.enums.SocialType;
import dobong.life.global.auth.exception.InvalidIDException;
import dobong.life.global.auth.exception.InvalidNickNameException;
import dobong.life.global.util.response.status.BaseErrorCode;
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

    private String nickName;

    @Column(nullable = true)
    private String providerId;

    @Enumerated(EnumType.STRING)
    @Column(name = "social_type", nullable = true)
    private SocialType providerType;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String password;

    private String imgUrl;

    public static User create(UserSignUpDto userSignUpDto, PasswordEncoder passwordEncoder){
        String password = passwordEncoder
                .encode(userSignUpDto.getPwd());

        isNickNameValid(userSignUpDto.getNickName());
        isIDValid(userSignUpDto.getId());

        return User.builder()
                .email(userSignUpDto.getId())
                .password(password)
                .nickName(userSignUpDto.getNickName())
                .role(Role.USER_REGULAR)
                .build();
    }

    public static User create(RegisterUserCommand registerUserCommand) {
        isNickNameValid(registerUserCommand.name());

        return User.builder()
                .email(registerUserCommand.email())
                .nickName(registerUserCommand.name())
                .password(null)
                .providerId(registerUserCommand.providerId())
                .providerType(registerUserCommand.providerType())
                .role(registerUserCommand.role())
                .build();
    }

    public static void isNickNameValid(String nickName) {
        for (char c : nickName.toCharArray()) {
            boolean isAlphabet = Character.isLetter(c);
            boolean isDigit = Character.isDigit(c);
            boolean isKorean = (c >= '가' && c <= '힣');

            if (!(isAlphabet || isDigit || isKorean)) {
                throw new InvalidNickNameException(BaseErrorCode.INVALID_NICKNAME);
            }
        }
        return;
    }

    public static void isIDValid(String Id) {
        for (char c : Id.toCharArray()) {
            boolean isAlphabet = Character.isLetter(c);
            boolean isDigit = Character.isDigit(c);

            if (!(isAlphabet || isDigit)) {
                throw new InvalidIDException(BaseErrorCode.INVALID_ID);
            }
        }
        return;
    }

    public static User create(Long id) { // test용
        return User.builder()
                .id(id)
                .email("test@naver.com")
                .nickName("test")
                .providerId("oauth2Id")
                .password("Oauth2!")
                .providerType(null)
                .role(Role.USER_OAUTH2).build();
    }

    public void updateImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void deleteImgUrl() {
        this.imgUrl = null;
    }

    public void updateNickName(String nickName) {
        this.nickName = nickName;
    }
}
