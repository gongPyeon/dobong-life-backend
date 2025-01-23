package dobong.life.dto;

import dobong.life.dto.info.StoreBasicInfo;
import dobong.life.dto.info.StoreGroup;
import dobong.life.dto.info.TagInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class StoreListResponseDto {
    private Long categoryId;
    private List<StoreGroup> results;
}
