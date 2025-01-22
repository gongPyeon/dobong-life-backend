package dobong.life.dto;

import dobong.life.dto.info.StoreBasicInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class StoreByFilterResponseDto {
    private Long categoryId;
    private List<StoreBasicInfo> contents;
}
