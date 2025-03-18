package dobong.life.base.store.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@AllArgsConstructor
@Getter
@Validated
public class StoresDTO {
    private String categoryName;
    private List<ItemDTO> items;
}
