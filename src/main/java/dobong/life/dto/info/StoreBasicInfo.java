package dobong.life.dto.info;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class StoreBasicInfo {
    private Long storeId;
    private String storeName;
    private String storeLocation;
    private String imgUrl;
    private boolean storeLike;
    private StoreBasicInfoDetails storeBasicInfoDetails;
}
