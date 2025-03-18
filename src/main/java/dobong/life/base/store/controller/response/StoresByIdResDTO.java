package dobong.life.base.store.controller.response;

import dobong.life.base.store.Domain;
import dobong.life.base.store.dto.HashTagDTO;
import dobong.life.base.store.dto.ItemDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@AllArgsConstructor
@Getter
@Validated
public class StoresByIdResDTO {
    private int currentPage;
    private int pageSize;
    private String categoryName;
    List<HashTagDTO> hashTagDTOList;
    List<ItemDTO> etcList;
}
