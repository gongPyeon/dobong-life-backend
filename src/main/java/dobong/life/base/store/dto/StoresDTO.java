package dobong.life.base.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@AllArgsConstructor
@Getter
public class StoresDTO {
    private String categoryName;
    private List<ItemDTO> items;
}
