package dobong.life.service.query;

import dobong.life.dto.info.MyPageReviewInfo;
import dobong.life.entity.Review;
import dobong.life.entity.ReviewLike;
import dobong.life.entity.User;
import dobong.life.repository.MiddleTagRepository;
import dobong.life.repository.ReviewLikeRepository;
import dobong.life.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyPageQueryService {

    private final ReviewRepository reviewRepository;
    private final ReviewLikeRepository reviewLikeRepository;
    private final MiddleTagRepository middleTagRepository;

    public List<MyPageReviewInfo> getMyPageReviewInfoList(User user) {
        return reviewRepository.findByUser(user).stream()
                .map(this::convertToMyPageReviewInfo)
                .collect(Collectors.toList());
    }

    public List<MyPageReviewInfo> getMyPageReviewLikeInfoList(User user) {
        return reviewLikeRepository.findByUser(user).stream()
                .map(ReviewLike::getReview)
                .map(this::convertToMyPageReviewInfo)
                .collect(Collectors.toList());
    }

    private MyPageReviewInfo convertToMyPageReviewInfo(Review review) {
        Long storeId = getDomainOrReviewId(review);
        String name = getReviewerName(review);
        List<String> selectedKeywords = getSelectedKeywords(review);
        String reviewContent = review.getContent();

        return new MyPageReviewInfo(storeId, name, selectedKeywords, reviewContent);
    }

    private long getDomainOrReviewId(Review review) {
        if (review.getDomain() != null) {
            return review.getDomain().getId();
        }
        return review.getId();
    }

    private String getReviewerName(Review review) {
        if(review.getUser() != null){
            return review.getUser().getName();
        }
        return null;
    }

    private List<String> getSelectedKeywords(Review review) {
        return middleTagRepository.findByReview(review).stream()
                .map(r -> r.getReviewTag().getName())
                .collect(Collectors.toList());
    }
}
