package dobong.life.dto.info;

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
    private List<RatingDetails> ratingDetails;
    private List<ReviewDetails> reviewDetails;
}
