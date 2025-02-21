package dobong.life.domain.user.service.query;

import dobong.life.domain.review.controller.request.MyPageReviewInfo;
import dobong.life.domain.review.Review;
import dobong.life.domain.like.ReviewLike;
import dobong.life.domain.user.User;
import dobong.life.domain.store.repository.MiddleTagRepository;
import dobong.life.domain.like.repository.ReviewLikeRepository;
import dobong.life.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public List<MyPageReviewInfo> getMyPageReviewLikeInfoList(User user) {
        return reviewLikeRepository.findByUser(user).stream()
                .map(ReviewLike::getReview)
                .filter(Objects::nonNull)
                .map(this::convertToMyPageReviewInfo)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    //TODO: N+1
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
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
