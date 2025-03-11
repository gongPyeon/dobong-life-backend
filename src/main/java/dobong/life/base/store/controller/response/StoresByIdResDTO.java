package dobong.life.base.store.controller.response;

import dobong.life.base.store.dto.HashTagDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@AllArgsConstructor
@Getter
@Validated
public class StoresByIdResDTO {
    private Long categoryId;
    private String categoryName;
    List<HashTagDTO> hashTagDTOList;
}
