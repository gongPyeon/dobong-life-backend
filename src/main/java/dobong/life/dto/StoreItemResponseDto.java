package dobong.life.dto;

import dobong.life.dto.info.StoreBasicInfo;
import dobong.life.dto.info.StoreGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class StoreItemResponseDto {
    private Long categoryId;
    private StoreBasicInfo result;
}
