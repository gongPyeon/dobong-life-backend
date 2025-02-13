package dobong.life.dto.info;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class StoreDetailInfo {
    private String subCategoryName;
    private String storeLocationDetail;
    private List<String> storeMenu;
    private List<String> storeKeyword;

    public static StoreDetailInfo create(){
        return StoreDetailInfo.builder()
                .subCategoryName("분식")
                .storeLocationDetail("서울시 도봉구")
                .storeMenu(List.of("순대1", "순대2"))
                .storeKeyword(List.of("행복1", "행복2"))
                .build();
    }
}
