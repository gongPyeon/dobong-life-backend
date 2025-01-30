package dobong.life.dto;

import dobong.life.dto.info.ReviewInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreReviewResponseDto {
    private Long categoryId;
    private Long storeId;
    private ReviewInfo result;
}
