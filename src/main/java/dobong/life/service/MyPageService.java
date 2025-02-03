package dobong.life.service;

import dobong.life.dto.MyPageResponseDto;
import dobong.life.dto.MyPageReviewResponseDto;
import dobong.life.dto.info.CountDetails;
import dobong.life.dto.info.MyPageReviewInfo;
import dobong.life.entity.User;
import dobong.life.service.query.MyPageQueryService;
import dobong.life.service.query.StoreQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MyPageService {

    private final StoreQueryService storeQueryService;
    private final MyPageQueryService myPageQueryService;
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
}
