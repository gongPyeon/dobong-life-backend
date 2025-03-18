package dobong.life.base.review.service;

import dobong.life.base.review.Middle;
import dobong.life.base.review.controller.request.ReviewReqDTO;
import dobong.life.base.review.service.query.KeywordQueryService;
import dobong.life.base.review.service.query.MiddleQueryService;
import dobong.life.base.review.service.query.ReviewQueryService;
import dobong.life.base.review.Review;
import dobong.life.base.store.Category;
import dobong.life.base.store.Domain;
import dobong.life.base.store.service.query.DomainQueryService;
import dobong.life.base.user.User;
import dobong.life.base.user.service.query.UserQueryService;
import dobong.life.global.util.response.status.BaseCode;
import dobong.life.global.util.response.status.BaseErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final UserQueryService userQueryService;
    private final DomainQueryService domainQueryService;
    private final ReviewQueryService reviewQueryService;
    private final MiddleQueryService middleQueryService;
    private final KeywordQueryService keywordQueryService;

    @Transactional
    public String saveReview(ReviewReqDTO reviewDTO, Long userId) {
        User user = userQueryService.getUserById(userId);
        Domain domain = domainQueryService.findById(reviewDTO.getStoreId());

        Review review = createReview(reviewDTO, user, domain);
        reviewQueryService.saveReview(review);

        List<Middle> middles = createMiddle(reviewDTO.getSelectedKeywords(), review, domain);
        middleQueryService.saveMiddle(middles);

        return BaseCode.SUCCESS_SAVE_REVIEW.getMessage();
    }

    private Review createReview(ReviewReqDTO reviewDTO, User user, Domain domain) {
        return new Review(
                reviewDTO.getReviewContent(),
                LocalDateTime.now(),
                user,
                domain
        );
    }

    private List<Middle> createMiddle(List<String> keywords, Review review, Domain domain) {
        return keywords.stream()
                .map(keyword -> new Middle(review, keywordQueryService.getKeyword(keyword), domain))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Transactional
    public String updateReviewLikeByUser(Long userId, Long reviewId) {
        User user = userQueryService.getUserById(userId);
        Review review = reviewQueryService.getReviewById(reviewId);

        reviewQueryService.updateReviewLike(user, review);

        return BaseCode.SUCCESS_UPDATE_LIKE.getMessage();
    }

    public String deleteReview(Long reviewId) {
        Review review = reviewQueryService.getReviewById(reviewId);
        reviewQueryService.deleteReview(review);
        return BaseCode.SUCCESS_DELETE_REVIEW.getMessage();
    }


}
