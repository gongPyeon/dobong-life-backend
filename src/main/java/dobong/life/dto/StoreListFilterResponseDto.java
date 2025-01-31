package dobong.life.dto;

import dobong.life.dto.info.StoreBasicInfo;
import dobong.life.dto.info.StoreGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class StoreListFilterResponseDto {
    private Long categoryId;
    private List<String> categoryNames;
    private List<String> subTagNames;
    private List<StoreBasicInfo> results;
}
