package dobong.life.service;

import dobong.life.dto.ReviewResDto;
import dobong.life.dto.info.MyPageReviewInfo;
import dobong.life.dto.info.ReviewInfo;
import dobong.life.entity.Domain;
import dobong.life.entity.MiddleTag;
import dobong.life.entity.Review;
import dobong.life.entity.User;
import dobong.life.service.principal.UserPrincipal;
import dobong.life.service.query.*;
import dobong.life.util.DEFINE;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewService {

    private final StoreQueryService storeQueryService;
    private final UserQueryService userQueryService;
    private final ReviewQueryService reviewQueryService;

    @Transactional
    public String saveReview(MyPageReviewInfo reviewInfo, UserPrincipal userPrincipal) {
        User user = userQueryService.getUserById(userPrincipal.getId());
        Domain domain = storeQueryService.getDomain(reviewInfo.getStoreId());

        Review review = createReview(reviewInfo, user, domain);
        reviewQueryService.saveReview(review);

        List<MiddleTag> middleTags = createMiddleTags(reviewInfo.getSelectedKeywords(), review);
        reviewQueryService.saveMiddleTag(middleTags);

        return DEFINE.REVIEW_OK;
    }

    public ReviewResDto getStoreReview(Long categoryId, Long storeId, Long userId) {
        User user = userQueryService.getUserById(userId);
        Domain domain = storeQueryService.getDomain(storeId);

        ReviewInfo reviewInfo = buildReviewInfo(domain, user);
        return new ReviewResDto(categoryId, storeId, reviewInfo);
    }

    private Review createReview(MyPageReviewInfo reviewInfo, User user, Domain domain) {
        return new Review(
                reviewInfo.getReviewContent(),
                LocalDateTime.now(),
                user,
                domain
        );
    }

    private List<MiddleTag> createMiddleTags(List<String> keywords, Review review) {
        return keywords.stream()
                .map(keyword -> new MiddleTag(review, reviewQueryService.getReviewTag(keyword)))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private ReviewInfo buildReviewInfo(Domain domain, User user) {
        return new ReviewInfo(
                reviewQueryService.getAverageRating(domain),
                reviewQueryService.getRatingCount(domain),
                reviewQueryService.getRatingDetails(domain),
                reviewQueryService.getReviewDetails(domain, user)
        );
    }
}
