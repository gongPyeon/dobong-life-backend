package dobong.life.base.store.controller.response;

import dobong.life.base.store.dto.ItemDTO;
import dobong.life.base.store.dto.ReviewsDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@Getter
@Validated
public class StoreResDTO {
    private ItemDTO itemDTO;
    private ReviewsDTO reviewsDTO;
}
