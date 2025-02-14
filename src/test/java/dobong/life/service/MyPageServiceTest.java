package dobong.life.service;

import dobong.life.dto.MyPageResDto;
import dobong.life.dto.MyPageReviewResDto;
import dobong.life.dto.info.MyPageReviewInfo;
import dobong.life.entity.User;
import dobong.life.service.query.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
@DisplayName("MyPageService를_테스트_한다")
class MyPageServiceTest {

    @InjectMocks
    private MyPageService myPageService;

    @Mock
    private TagQueryService tagQueryService;

    @Mock
    private MyPageQueryService myPageQueryService;

    @Mock
    private ReviewQueryService reviewQueryService;

    @Mock
    private UserQueryService userQueryService;

    @Mock
    private ReviewLikeQueryService reviewLikeQueryService;

    @Mock
    private CategoryQueryService categoryQueryService;

    User testUser;

    @BeforeEach
    void signUp(){
        testUser = User.create(1L);
    }

    @Nested
    @DisplayName("마이페이지 조회 Service 실행 시")
    class GetMyPage{
        @Test
        @DisplayName("성공")
        void getMyPage_success(){

            // given
            Long userId = 1L;
            int reviewCount = 1;
            int reviewLikeCount = 1;
            int foodLikeCount = 2;
            int placeLikeCount = 2;
            int businessLikeCount = 2;

            given(userQueryService.getUserById(anyLong())).willReturn(testUser);
            given(reviewQueryService.getReviewCount(any())).willReturn(reviewCount);
            given(reviewLikeQueryService.getReviewLikeCount(any())).willReturn(reviewLikeCount);

            given(reviewLikeQueryService.getMyCategoryLikeCount(any(), anyLong())).willReturn(foodLikeCount);
            given(reviewLikeQueryService.getMyCategoryLikeCount(any(), anyLong())).willReturn(placeLikeCount);
            given(reviewLikeQueryService.getMyCategoryLikeCount(any(), anyLong())).willReturn(businessLikeCount);

            //when
            MyPageResDto result = myPageService.getMyPage(userId);

            // then
            assertThat(result).isNotNull();
            assertThat(result.getMyReviewCount()).isEqualTo(reviewCount);
            assertThat(result.getMyReviewLikeCount()).isEqualTo(reviewLikeCount);
            assertThat(result.getMyFoodLike()).isEqualTo(foodLikeCount);
            assertThat(result.getMyPlaceLike()).isEqualTo(placeLikeCount);
            assertThat(result.getMyBusinessLike()).isEqualTo(businessLikeCount);

            then(userQueryService).should().getUserById(userId);
            then(reviewQueryService).should().getReviewCount(testUser);
            then(reviewLikeQueryService).should().getReviewLikeCount(testUser);
            then(reviewLikeQueryService).should().getMyCategoryLikeCount(testUser, 1L);
            then(reviewLikeQueryService).should().getMyCategoryLikeCount(testUser, 2L);
            then(reviewLikeQueryService).should().getMyCategoryLikeCount(testUser, 3L);

        }
    }

    @Nested
    @DisplayName("마이페이지 리뷰 조회 Service 실행 시")
    class GetMyReview{
        @Test
        @DisplayName("성공")
        void getMyReview_success(){

            // given
            Long userId = 1L;
            List<MyPageReviewInfo> testReviews = Collections.singletonList(
                    MyPageReviewInfo.create(1L)
            );

            given(userQueryService.getUserById(anyLong())).willReturn(testUser);
            given(myPageQueryService.getMyPageReviewInfoList(any())).willReturn(testReviews);

            //when
            MyPageReviewResDto result = myPageService.getMyReview(userId);

            // then
            assertThat(result).isNotNull();
            Assertions.assertThat(result.getReviews()).hasSize(1);

            then(userQueryService).should().getUserById(userId);
            then(myPageQueryService).should().getMyPageReviewInfoList(testUser);
        }
    }

}