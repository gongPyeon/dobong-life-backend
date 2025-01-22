package dobong.life.dto;

import dobong.life.dto.info.StoreBasicInfo;
import dobong.life.dto.info.TagInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class StoreListMoreResponseDto {
    private Long categoryId;
    private TagInfo tag;
    private List<StoreBasicInfo> contents;
}
