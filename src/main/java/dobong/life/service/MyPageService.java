package dobong.life.service;

import dobong.life.dto.MyPageResponseDto;
import dobong.life.dto.MyPageReviewResponseDto;
import dobong.life.dto.StoreItemListResponseDto;
import dobong.life.dto.StoreItemResponseDto;
import dobong.life.dto.info.CountDetails;
import dobong.life.dto.info.MyPageReviewInfo;
import dobong.life.dto.info.StoreBasicInfo;
import dobong.life.entity.*;
import dobong.life.service.mapper.StoreMapper;
import dobong.life.service.query.MyPageQueryService;
import dobong.life.service.query.ReviewQueryService;
import dobong.life.service.query.StoreQueryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MyPageService {

    private final StoreQueryService storeQueryService;
    private final MyPageQueryService myPageQueryService;
    private final ReviewQueryService reviewQueryService;
    private final StoreMapper storeMapper;

    public MyPageResponseDto getMyPage(String email) {
        User user = storeQueryService.getUserByEmail(email);
        CountDetails countDetails = myPageQueryService.getCountDetails(user);

        return MyPageResponseDto.builder()
                .email(user.getEmail())
                .name(user.getName())
                .myReviewCount(countDetails.getMyReviewCount())
                .myReviewLikeCount(countDetails.getMyReviewLikeCount())
                .myFoodLike(countDetails.getMyFoodLike())
                .myPlaceLike(countDetails.getMyPlaceLike())
                .myBusinessLike(countDetails.getMyBusinessLike())
                .build();
    }

    public MyPageReviewResponseDto getMyReview(String email) {
        User user = storeQueryService.getUserByEmail(email);
        List<MyPageReviewInfo> reviews  = myPageQueryService.getMyPageReviewInfoList(user);

        return MyPageReviewResponseDto.builder()
                .reviews(reviews)
                .build();
    }

    public MyPageReviewResponseDto getMyReviewLike(String email) {
        User user = storeQueryService.getUserByEmail(email);
        List<MyPageReviewInfo> reviews  = myPageQueryService.getMyPageReviewLikeInfoList(user);

        return MyPageReviewResponseDto.builder()
                .reviews(reviews)
                .build();
    }

    public StoreItemListResponseDto getMyLike(Long categoryId, String email) {
        Category category = storeQueryService.getCategory(categoryId);
        User user = storeQueryService.getUserByEmail(email);
        List<StoreBasicInfo> results = storeQueryService.getStoreLike(category, user).stream()
                .map(d -> createStoreInfoDetail(d, user)).collect(Collectors.toList());

        return StoreItemListResponseDto.builder()
                .categoryId(categoryId)
                .result(results)
                .build();
    }

    private StoreBasicInfo createStoreInfoDetail(Domain domain, User user) {
        boolean isFavorite = storeQueryService.isUserFavorite(domain, user);
        List<String> items = storeQueryService.getItems(domain);
        List<String> keywords = storeQueryService.getHashTags(domain);
        return storeMapper.toStoreBasicInfoDetail(domain, isFavorite, "", items, keywords);
    }

    @Transactional
    public void saveReview(MyPageReviewInfo r, String email) {
        User user = storeQueryService.getUserByEmail(email);
        Domain domain = storeQueryService.getStore(r.getStoreId());
        Review review = new Review(r.getReviewContent(), 0, LocalDateTime.now(),0.0, user, domain);
        reviewQueryService.saveReview(review);

        List<String> selectedKeywords = r.getSelectedKeywords();
        List<MiddleTag> collect = selectedKeywords.stream()
                .map(s -> new MiddleTag(review, reviewQueryService.getReviewTag(s))).collect(Collectors.toList());
        reviewQueryService.saveMiddleTag(collect);
    }
}
