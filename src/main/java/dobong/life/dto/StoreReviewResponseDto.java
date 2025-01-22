package dobong.life.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class StoreReviewResponseDto {
    private Long categoryId;
    private Long storeId;
    private Result result;

    @Getter
    @NoArgsConstructor
    public class Result{
        private double averageRating;
        private int ratingCount;
        private List<RatingDetails> ratingDetails;
        private List<ReviewDetails> reviewDetails;
    }

    @Getter
    @NoArgsConstructor
    public class RatingDetails{
        private String reviewTag;
        private int ratingTag;
    }

    @Getter
    @NoArgsConstructor
    public class ReviewDetails{
        private String userName;
        private String userImage;
        private int userReviewCount;
        private String reviewDate;
        private String reviewContent;
        private List<KeyWord> selectedKeywords;
        private boolean likedByUser;
        private int likeCount;

        @Getter
        @NoArgsConstructor
        public class KeyWord{
            private String name;
        }
    }
}
