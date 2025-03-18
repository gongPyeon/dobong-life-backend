package dobong.life.base.store.service;

import dobong.life.base.review.Review;
import dobong.life.base.review.service.query.MiddleQueryService;
import dobong.life.base.review.service.query.ReviewQueryService;
import dobong.life.base.store.Category;
import dobong.life.base.store.Domain;
import dobong.life.base.store.controller.response.StoreResDTO;
import dobong.life.base.store.dto.ItemDTO;
import dobong.life.base.store.dto.ItemDetailDTO;
import dobong.life.base.store.dto.ReviewDTO;
import dobong.life.base.store.dto.ReviewsDTO;
import dobong.life.base.store.service.query.CategoryQueryService;
import dobong.life.base.store.service.query.DomainQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreAndReviewService {

    private final MiddleQueryService middleQueryService;
    private final DomainQueryService domainQueryService;
    private final ReviewQueryService reviewQueryService;
    private final CategoryQueryService categoryQueryService;

    public StoreResDTO getStore(Long userId, Long storeId) {
        ItemDetailDTO itemDetailDTO = getItemDetailDTO(userId, storeId);
        ReviewsDTO reviewsDTO = getReviewsDTO(storeId);

        return new StoreResDTO(itemDetailDTO, reviewsDTO);
    }

    private ReviewsDTO getReviewsDTO(Long storeId) {
        Domain domain = domainQueryService.findById(storeId);

        List<String> keywords = middleQueryService.sortKeywords(domain);
        double averageRating = reviewQueryService.calculateRating(domain);
        int reviewCount = reviewQueryService.getReviewCount(domain);
        List<ReviewDTO> reviewDTOList = getReviewDTO(domain);

        return new ReviewsDTO(keywords, averageRating, reviewCount, reviewDTOList);
    }

    private List<ReviewDTO> getReviewDTO(Domain domain) {
        List<ReviewDTO> reviewDTOList = new ArrayList<>();

        List<Review> reviews = reviewQueryService.findByDomain(domain);

        for(Review review : reviews){
            Long reviewId = review.getId();
            String userName = review.getUser().getNickName();
            LocalDateTime reviewDate = review.getDate();
            String reviewContent = review.getContent();
            List<String> selectedKeywords = middleQueryService.getKeywords(review);
            boolean likeByUser = reviewQueryService.likeByUser(review.getUser(), review);
            int likeCount = review.getLikeCount();
            reviewDTOList.add(new ReviewDTO(reviewId, userName, reviewDate, reviewContent, selectedKeywords, likeByUser, likeCount));
        }
        return reviewDTOList;
    }

    private ItemDetailDTO getItemDetailDTO(Long userId, Long storeId){
        Domain domain = domainQueryService.findById(storeId);
        Category categoryByDomain = domain.getCategory();
        List<String> categories = categoryQueryService.getCategories(categoryByDomain);
        boolean like = domainQueryService.getLikeByUser(domain, userId);

        return new ItemDetailDTO(domain.getName(), domain.getAddress(), categories, domain.getStartTime(),
                domain.getEndTime(), domain.getDay(), domain.getDescription(), like);
    }
}
