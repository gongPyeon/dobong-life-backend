package dobong.life.base.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@AllArgsConstructor
@Getter
@Builder
@Validated
public class ReviewsDTO {
    private List<String> keywords;
    private double averageRating;
    private int reviewCount;
    List<ReviewDTO> reviewDTOList;
}
