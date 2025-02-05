package dobong.life.dto.info;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class StoreDetailInfo {
    private String subCategoryName;
    private String storeLocationDetail;
    private List<String> storeMenu;
    private List<String> storeKeyword;
}
