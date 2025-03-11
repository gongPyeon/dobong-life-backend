package dobong.life.base.store.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@AllArgsConstructor
@Getter
@Validated
public class HashTagDTO {
    private String hashTagName;
    private List<ItemDTO> items;
}
