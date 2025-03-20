package dobong.life.base.review.controller.response;

import dobong.life.base.review.dto.ReviewDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ReviewsResDTO {
    List<ReviewDTO> reviewDTOList;
}
