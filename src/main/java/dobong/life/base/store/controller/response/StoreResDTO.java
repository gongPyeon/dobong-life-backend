package dobong.life.base.store.controller.response;

import dobong.life.base.store.dto.ItemDTO;
import dobong.life.base.store.dto.ItemDetailDTO;
import dobong.life.base.store.dto.ReviewsDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@Getter
@Builder
@Validated
public class StoreResDTO {
    private ItemDetailDTO itemDetailDTO;
    private ReviewsDTO reviewsDTO;
}
