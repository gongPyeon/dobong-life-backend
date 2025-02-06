package dobong.life.dto;

import dobong.life.dto.info.StoreBasicInfo;
import dobong.life.dto.info.StoreDetailInfo;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.checkerframework.checker.units.qual.N;

@Getter
@AllArgsConstructor
public class StoreItemResDto {
    @NotNull
    private Long categoryId;
    private StoreBasicInfo storeBasicInfo;
    private StoreDetailInfo storeDetailInfo;
}
