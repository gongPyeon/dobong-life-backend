package dobong.life.dto;

import dobong.life.dto.info.StoreBasicInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class StoreItemListResponseDto {
    private Long categoryId;
    private List<StoreBasicInfo> result;
}
