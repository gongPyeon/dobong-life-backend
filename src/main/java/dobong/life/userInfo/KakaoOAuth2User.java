package dobong.life.userInfo;

import java.util.Map;

public class KakaoOAuth2User extends OAuth2UserInfo{

    private final Map<String, Object> account;
    public KakaoOAuth2User(Map<String, Object> attributes) {
        super(attributes);
        account = (Map<String, Object>) attributes.get("kakao_account");
    }

    @Override
    public String getOAuth2Id() {
        return String.valueOf(attributes.get("id"));
    }

    @Override
    public String getEmail() { return String.valueOf(account.get("email")); }

    @Override
    public String getName() {
        Map<String, Object> profile = (Map<String, Object>) account.get("profile");
        return String.valueOf(profile.get("nickname"));
    }
}
