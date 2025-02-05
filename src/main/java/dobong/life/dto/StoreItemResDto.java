package dobong.life.dto;

import dobong.life.dto.info.StoreBasicInfo;
import dobong.life.dto.info.StoreDetailInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StoreItemResDto {
    private Long categoryId;
    private StoreBasicInfo storeBasicInfo;
    private StoreDetailInfo storeDetailInfo;
}
