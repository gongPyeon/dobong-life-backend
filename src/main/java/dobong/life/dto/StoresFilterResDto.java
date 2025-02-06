package dobong.life.dto;

import dobong.life.dto.info.StoreBasicInfo;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class StoresFilterResDto {
    @NotNull
    private Long categoryId;
    private List<String> categoryNames;
    private List<String> subTagNames;
    private List<StoreBasicInfo> items;

}
