package dobong.life.dto.info;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

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
        return ItemInfo.builder()
                .tagId(tagId)
                .build();
    }
}
