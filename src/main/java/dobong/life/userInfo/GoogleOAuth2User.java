package dobong.life.userInfo;

import java.util.Map;

public class GoogleOAuth2User extends OAuth2UserInfo{

    public GoogleOAuth2User(Map<String, Object> attributes){
        super(attributes);
    }

    @Override
    public String getOAuth2Id() {
        return String.valueOf(attributes.get("sub"));
    }

    @Override
    public String getEmail() {
        return String.valueOf(attributes.get("email"));
    }

    @Override
    public String getName() {
        return String.valueOf(attributes.get("name"));
    }
}
