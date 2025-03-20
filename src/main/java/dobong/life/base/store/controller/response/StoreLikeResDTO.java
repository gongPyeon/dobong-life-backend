package dobong.life.base.store.controller.response;

import dobong.life.base.store.dto.ItemDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class StoreLikeResDTO {
    List<ItemDTO> itemDTOList;
}
