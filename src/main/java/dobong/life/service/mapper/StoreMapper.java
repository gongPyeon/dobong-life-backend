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
                .storeName(domain.getName())
                .storeLocation(domain.getAddressDong())
                .imgUrl(domain.getImageUrl())
                .storeLike(isFavorite)
                .build();
    }

    public StoreBasicInfo toStoreBasicInfoDetail(Domain domain, boolean isFavorite, String subCategory, List<String> items, List<String> keywords) {
        return StoreBasicInfo.builder()
                .storeId(domain.getId())
                .storeName(domain.getName())
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

    public ReviewInfo createReviewInfo(double averageRating, int ratingCount, List<RatingDetails> ratingDetails, List<ReviewDetails> reviewDetails) {
        return ReviewInfo.builder()
                .averageRating(averageRating)
                .ratingCount(ratingCount)
                .ratingDetails(ratingDetails)
                .reviewDetails(reviewDetails)
                .build();
    }
}
