package dobong.life.service.query;

import dobong.life.dto.info.RatingDetailInfo;
import dobong.life.dto.info.ReviewDetailInfo;
import dobong.life.entity.*;
import dobong.life.repository.MiddleTagRepository;
import dobong.life.repository.ReviewLikeRepository;
import dobong.life.repository.ReviewRepository;
import dobong.life.repository.ReviewTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;
    private final ReviewTagRepository reviewTagRepository;
    private final MiddleTagRepository middleTagRepository;
    private final ReviewLikeRepository reviewLikeRepository;

    public double getAverageRating(Domain domain) {
        return reviewRepository.findByDomain(domain).stream()
                .mapToDouble(r -> r.getScore()).average().orElse(0.0);
    }

    public int getRatingCount(Domain domain) {
        return (int) reviewRepository.findByDomain(domain).stream().count();
    }

    public List<RatingDetailInfo> getRatingDetails(Domain domain) {
        return reviewTagRepository.findAll().stream()
                .map(r -> createRatingDetail(r, domain))
                .collect(Collectors.toList());
    }

    private RatingDetailInfo createRatingDetail(ReviewTag reviewTag, Domain domain) {
        int ratingTag = reviewRepository.findByDomain(domain).stream()
                .mapToInt(r -> createRatingTag(r, reviewTag)).sum();
        return new RatingDetailInfo(reviewTag.getName(), ratingTag);
    }

    private int createRatingTag(Review review, ReviewTag reviewTag) {
        return (int) middleTagRepository.findByReviewTagAndReview(reviewTag, review).stream().count();
    }

    public List<ReviewDetailInfo> getReviewDetails(Domain domain, User user) {
        return reviewRepository.findByDomain(domain).stream()
                .map(r -> createReviewDetails(r, domain, user))
                .collect(Collectors.toList());
    }

    private ReviewDetailInfo createReviewDetails(Review review, Domain domain, User user) {
        User usr = review.getUser();
        int userReviewCount = (int) reviewRepository.findByUser(usr).stream().count();
        List<String> selectedKeywords = middleTagRepository.findByReview(review).stream()
                .map(r -> r.getReviewTag().getName()).collect(Collectors.toList());

        boolean likeByUser = reviewLikeRepository.findByUserAndReview(user, review).isPresent();

        return new ReviewDetailInfo(review.getId(), user.getName(), userReviewCount, review.getDate(),
                review.getContent(), selectedKeywords, likeByUser, review.getLikeCount());
    }

    public ReviewTag getReviewTag(String name) {
        return reviewTagRepository.findByName(name);
    }

    public void saveReview(Review review) {
        reviewRepository.save(review);
    }

    public int getReviewCount(User user) {
        return reviewRepository.findByUser(user).size();
    }

    public void saveMiddleTag(List<MiddleTag> collect) {
        collect.stream().forEach(middleTagRepository::save);
    }

    public Review getReviewById(Long reviewId) {
        return reviewRepository.findById(reviewId).get();
    }
}
