package dobong.life.dto;

import dobong.life.dto.info.ItemInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class StoresResDto {
    private Long categoryId;
    private List<ItemInfo> items;
}
