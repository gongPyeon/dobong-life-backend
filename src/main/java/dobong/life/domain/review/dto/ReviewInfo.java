package dobong.life.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ReviewInfo{
    private double averageRating;
    private int ratingCount;
    private List<RatingDetailInfo> ratingDetails;
    private List<ReviewDetailInfo> reviewDetails;
}
