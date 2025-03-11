package dobong.life.base.store.controller.response;

import dobong.life.base.store.dto.ItemDTO;
import dobong.life.base.store.dto.StoresDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@AllArgsConstructor
@Getter
@Validated
public class StoresResDTO {
    private List<StoresDTO> storesDTOS;
}
