package dobong.life.dto.info;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class StoreBasicInfoDetails {
    private String subCategory;
    private String storeLocationDetail;
    private List<String> storeMenu;
    private List<String> storeKeyword;
}
