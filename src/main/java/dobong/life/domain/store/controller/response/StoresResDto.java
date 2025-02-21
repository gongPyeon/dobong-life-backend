package dobong.life.domain.store.controller.response;

import dobong.life.domain.store.dto.ItemInfo;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@AllArgsConstructor
@Getter
@Validated
public class StoresResDto {
    @NotNull
    private Long categoryId;
    private List<ItemInfo> items;
}
