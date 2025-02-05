package dobong.life.dto;

import dobong.life.dto.info.ReviewInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReviewResDto {
    private Long categoryId;
    private Long storeId;
    private ReviewInfo result;
}
