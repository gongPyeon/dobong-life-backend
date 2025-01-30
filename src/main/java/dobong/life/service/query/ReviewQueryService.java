package dobong.life.service.query;

import dobong.life.dto.info.ReviewInfoGroup;
import dobong.life.entity.Domain;
import dobong.life.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {
    public ReviewInfoGroup getReviewInfoGroup(Domain domain, User user) {
        return null;
    }

    /*
    *  private double averageRating;
    private int ratingCount;
    private List<Review> reviews;
    private int reviewCount;
    private boolean isFavorite;
    private List<String> keywords;
    private ReviewImage reviewImage;
    private List<ReviewTag> reviewTags;
    * */
}
