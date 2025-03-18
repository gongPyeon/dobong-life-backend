package dobong.life.base.review.service.query;

import dobong.life.base.review.Middle;
import dobong.life.base.review.ReviewLike;
import dobong.life.base.review.exception.ReviewNotFoundException;
import dobong.life.base.review.repository.MiddleRepository;
import dobong.life.base.review.repository.ReviewLikeRepository;
import dobong.life.base.review.repository.ReviewRepository;
import dobong.life.base.review.Review;
import dobong.life.base.store.dto.ReviewDTO;
import dobong.life.base.user.User;
import dobong.life.base.store.Domain;
import dobong.life.global.util.response.status.BaseErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {
    private final ReviewRepository reviewRepository;
    private final ReviewLikeRepository reviewLikeRepository;
    private final MiddleRepository middleRepository;

    @Transactional
    public void saveReview(Review review) {
        reviewRepository.save(review);
    }

    public Review getReviewById(Long reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException(BaseErrorCode.NOT_FOUND,
                        "[ERROR] "+reviewId+"에 해당하는 리뷰를 찾을 수 없습니다"));

    }

    @Transactional
    public void updateReviewLike(User user, Review review) {
        if(checkReviewLike(user, review)) {
            removeReviewLike(user, review);
            return;
        }

        ReviewLike reviewLike = new ReviewLike(user, review);
        reviewLikeRepository.save(reviewLike);
        review.updateLikeCount();
    }

    private boolean checkReviewLike(User user, Review review) {
        return reviewLikeRepository.findByUserAndReview(user, review).isPresent();
    }

    private ReviewLike getReviewLike(User user, Review review) {
        return reviewLikeRepository.findByUserAndReview(user, review)
                .orElseThrow(() -> new ReviewNotFoundException(BaseErrorCode.NOT_FOUND,
                        "[ERROR] "+review.getId()+"에 해당하는 리뷰를 찾을 수 없습니다"));
    }

    @Transactional
    public void removeReviewLike(User user, Review review){
        reviewLikeRepository.delete(getReviewLike(user, review));
    }

    @Transactional
    public void deleteReview(Review review) {
        List<Middle> middles = middleRepository.findByReview(review).orElseThrow(
                ()->new ReviewNotFoundException(BaseErrorCode.NOT_FOUND, "[ERROR] 리뷰 중간 테이블을 찾을 수 없습니다"));
        middleRepository.deleteAll(middles);
        reviewRepository.delete(review);
    }

    public double calculateRating(Domain domain) {
        return reviewRepository.findByDomain(domain).get().stream()
                .mapToDouble(r -> r.getScore()).average().orElse(0.0);
    }

    public int getReviewCount(Domain domain) {
        return (int) reviewRepository.findByDomain(domain).stream().count();
    }

    public List<Review> findByDomain(Domain domain) {
        return reviewRepository.findByDomain(domain).orElseThrow(() -> new ReviewNotFoundException(BaseErrorCode.NOT_FOUND,
                "[ERROR] "+domain.getName()+"에 해당하는 리뷰를 찾을 수 없습니다"));
    }

    public boolean likeByUser(User user, Review review) {
        return reviewLikeRepository.findByUserAndReview(user, review).isPresent();
    }

    public List<Review> findByUserId(Long userId) {
        return reviewRepository.findByUserId(userId);
    }
}
