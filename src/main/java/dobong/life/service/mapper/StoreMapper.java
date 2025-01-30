package dobong.life.service.mapper;

import dobong.life.dto.info.*;
import dobong.life.entity.*;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.devtools.v129.domstorage.model.Item;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class StoreMapper {
    public TagInfo toTagInfo(Tag tag, SubTag subTag) {
        return TagInfo.builder()
                .tagId(tag.getId())
                .parentTagName(tag.getParentTagName())
                .subTagName(subTag.getSubTagName())
                .build();
    }

    public StoreBasicInfo toStoreBasicInfo(Domain domain, boolean isFavorite) {
        return StoreBasicInfo.builder()
                .storeId(domain.getId())
                .storeName(domain.getNameKr())
                .storeLocation(domain.getAddressDong())
                .imgUrl(domain.getImageUrl())
                .storeLike(isFavorite)
                .build();
    }

    public StoreBasicInfo toStoreBasicInfoDetail(Domain domain, boolean isFavorite, String subCategory, List<String> items, List<String> keywords) {
        return StoreBasicInfo.builder()
                .storeId(domain.getId())
                .storeName(domain.getNameKr())
                .storeLocation(domain.getAddressDong())
                .imgUrl(domain.getImageUrl())
                .storeLike(isFavorite)
                .storeBasicInfoDetails(createDetail(domain, subCategory, items, keywords))
                .build();
    }

    private StoreBasicInfoDetails createDetail(Domain domain, String subCategory, List<String> items, List<String> keywords) {
        return StoreBasicInfoDetails.builder()
                .subCategory(subCategory)
                .storeLocationDetail(domain.getAddressDetail())
                .storeKeyword(keywords)
                .storeMenu(items)
                .build();
    }

    public ReviewInfo createReviewInfo(ReviewInfoGroup reviewInfoGroup){
        return ReviewInfo.builder()
                .averageRating(reviewInfoGroup.getAverageRating())
                .ratingCount(reviewInfoGroup.getRatingCount())

                .reviewDetails(createReviewDetails(reviewInfoGroup.getReviews(),
                        reviewInfoGroup.getReviewCount(), reviewInfoGroup.isFavorite(),
                        reviewInfoGroup.getKeywords(), reviewInfoGroup.getReviewImage()))

                .ratingDetails(createRatingDetails(reviewInfoGroup.getReviewTags()))
                .build();
    }

    private List<RatingDetails> createRatingDetails(List<ReviewTag> reviewTags) {
        return reviewTags.stream()
                .map(reviewTag -> getRatingDetails(reviewTag))
                .collect(Collectors.toList());
    }

    private RatingDetails getRatingDetails(ReviewTag reviewTag) {
        return RatingDetails.builder()
                .reviewTag(reviewTag.getName())
                .ratingTag(reviewTag.getCount())
                .build();
    }

    private List<ReviewDetails> createReviewDetails(List<Review> reviews, int reviewCount, boolean isFavorite, List<String> keywords, ReviewImage reviewImage) {
        return reviews.stream()
                .map(review -> getReviewDetails(reviewCount, isFavorite, keywords, reviewImage, review))
                .collect(Collectors.toList());
    }

    private ReviewDetails getReviewDetails(int reviewCount, boolean isFavorite, List<String> keywords, ReviewImage reviewImage, Review review) {
        return ReviewDetails.builder()
                .userName(reviewImage.getFileName())
                .userImage(reviewImage.getFileName())
                .userReviewCount(reviewCount)
                .reviewDate(review.getDate())
                .reviewContent(review.getContent())
                .selectedKeywords(keywords)
                .likedByUser(isFavorite)
                .likeCount(review.getLikeCount())
                .build();
    }
}
