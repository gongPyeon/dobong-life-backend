package dobong.life.domain.store.controller.response;

import dobong.life.domain.store.dto.StoreBasicInfo;
import dobong.life.domain.store.dto.StoreDetailInfo;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StoreItemResDto {
    @NotNull
    private Long categoryId;
    private StoreBasicInfo storeBasicInfo;
    private StoreDetailInfo storeDetailInfo;
}
