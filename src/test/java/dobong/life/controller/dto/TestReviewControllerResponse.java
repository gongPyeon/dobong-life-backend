package dobong.life.controller.dto;

import dobong.life.dto.ReviewResDto;
import dobong.life.dto.info.RatingDetailInfo;
import dobong.life.dto.info.ReviewDetailInfo;
import dobong.life.dto.info.ReviewInfo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TestReviewControllerResponse {

    public static ReviewResDto makeTestGetReviewResDto(Long categoryId, Long storeId, double averageRating, int ratingCount,
                                                       Long reviewId, String userName, int userReviewCount, String reviewContent,
                                                       List<String> selectedKeywords, boolean likedByUser, int likeCount,
                                                       String reviewTag, int ratingTag){

        List<RatingDetailInfo> ratingDetails = new ArrayList<>();
        ratingDetails.add(new RatingDetailInfo(reviewTag, ratingTag));

        List<ReviewDetailInfo> reviewDetails = new ArrayList<>();
        reviewDetails.add(new ReviewDetailInfo(reviewId, userName, userReviewCount, LocalDateTime.now(),
                reviewContent, selectedKeywords, likedByUser, likeCount));

        ReviewInfo result = new ReviewInfo(averageRating, ratingCount, ratingDetails, reviewDetails);
        return new ReviewResDto(categoryId, storeId, result);
    }
}
