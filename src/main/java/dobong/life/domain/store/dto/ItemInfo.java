package dobong.life.domain.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class ItemInfo {
    private Long tagId;
    private String parentTagName;
    private Long subTagId;
    private String subTagName;
    private List<StoreBasicInfo> stores;

    public static ItemInfo create(Long tagId) {
        List<StoreBasicInfo> stores = new ArrayList<>();
        stores.add(new StoreBasicInfo(1L, "순대", "location", "img", true));

        return ItemInfo.builder()
                .tagId(tagId)
                .parentTagName("행복")
                .subTagId(1L)
                .subTagName("소소한")
                .stores(stores)
                .build();
    }
}
