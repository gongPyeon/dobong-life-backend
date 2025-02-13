package dobong.life.dto.info;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class StoreBasicInfo {
    private Long storeId;
    private String storeName;
    private String storeLocation;
    private String imgUrl;
    private boolean storeLike;


    public static StoreBasicInfo create(Long storeId) {
        return StoreBasicInfo.builder()
                .storeId(storeId)
                .storeName("순대집")
                .storeLocation("도봉동")
                .imgUrl("이미지")
                .storeLike(true)
                .build();
    }
}
