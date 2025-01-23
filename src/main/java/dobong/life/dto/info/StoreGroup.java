package dobong.life.dto.info;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class StoreGroup {
    private TagInfo tag;
    private List<StoreBasicInfo> stores;
}
