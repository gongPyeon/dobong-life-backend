package dobong.life.service;

import dobong.life.dto.MyPageResponseDto;
import dobong.life.dto.MyPageReviewResponseDto;
import dobong.life.dto.StoreItemListResponseDto;
import dobong.life.dto.StoreItemResponseDto;
import dobong.life.dto.info.CountDetails;
import dobong.life.dto.info.MyPageReviewInfo;
import dobong.life.dto.info.StoreBasicInfo;
import dobong.life.entity.Category;
import dobong.life.entity.Domain;
import dobong.life.entity.User;
import dobong.life.service.mapper.StoreMapper;
import dobong.life.service.query.MyPageQueryService;
import dobong.life.service.query.StoreQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MyPageService {

    private final StoreQueryService storeQueryService;
    private final MyPageQueryService myPageQueryService;
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
        List<MyPageReviewInfo> results  = myPageQueryService.getMyPageReviewInfoList(user);

        return MyPageReviewResponseDto.builder()
                .results(results)
                .build();
    }

    public MyPageReviewResponseDto getMyReviewLike(String email) {
        User user = storeQueryService.getUserByEmail(email);
        List<MyPageReviewInfo> results  = myPageQueryService.getMyPageReviewLikeInfoList(user);

        return MyPageReviewResponseDto.builder()
                .results(results)
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
}
