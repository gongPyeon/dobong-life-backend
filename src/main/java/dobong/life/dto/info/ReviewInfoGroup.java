package dobong.life.dto.info;

import dobong.life.entity.Review;
import dobong.life.entity.ReviewImage;
import dobong.life.entity.ReviewTag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class ReviewInfoGroup {
    private double averageRating;
    private int ratingCount;
    private List<Review> reviews;
    private int reviewCount;
    private boolean isFavorite;
    private List<String> keywords;
    private ReviewImage reviewImage;
    private List<ReviewTag> reviewTags;
}
