package dobong.life.dto.info;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ItemInfo {
    private Long tagId;
    private String parentTagName;
    private Long subTagId;
    private String subTagName;
    private List<StoreBasicInfo> stores;
}
