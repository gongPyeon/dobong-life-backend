package dobong.life.domain.service;

import dobong.life.domain.like.service.query.ReviewLikeQueryService;
import dobong.life.domain.review.service.query.ReviewQueryService;
import dobong.life.domain.store.service.query.CategoryQueryService;
import dobong.life.domain.store.service.query.TagQueryService;
import dobong.life.domain.user.controller.response.MyPageResDto;
import dobong.life.domain.user.controller.response.MyPageReviewResDto;
import dobong.life.domain.review.controller.request.MyPageReviewInfo;
import dobong.life.domain.user.service.MyPageService;
import dobong.life.domain.user.service.query.MyPageQueryService;
import dobong.life.domain.user.service.query.UserQueryService;
import dobong.life.domain.store.dto.StoreBasicInfo;
import dobong.life.domain.store.Category;
import dobong.life.domain.user.User;
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

    @Nested
    @DisplayName("마이페이지 좋아요한 리뷰 조회 Service 실행 시")
    class GetMyReviewLike{
        @Test
        @DisplayName("성공")
        void getMyReviewLike_success(){

            // given
            Long userId = 1L;
            List<MyPageReviewInfo> testReviews = Collections.singletonList(
                    MyPageReviewInfo.create(1L)
            );

            given(userQueryService.getUserById(anyLong())).willReturn(testUser);
            given(myPageQueryService.getMyPageReviewLikeInfoList(any())).willReturn(testReviews);

            //when
            MyPageReviewResDto result = myPageService.getMyReviewLike(userId);

            // then
            assertThat(result).isNotNull();
            Assertions.assertThat(result.getReviews()).hasSize(1);

            then(userQueryService).should().getUserById(userId);
            then(myPageQueryService).should().getMyPageReviewLikeInfoList(testUser);
        }
    }

    @Nested
    @DisplayName("마이페이지 좋아요한 상점 조회 Service 실행 시")
    class GetMyStoreLike{
        @Test
        @DisplayName("성공")
        void getMyStoreLike_success(){

            // given
            Category testCategory = Category.create(1L);
            Long categoryId = 1L;
            Long userId = 1L;
            Long storeId = 1L;
            List<StoreBasicInfo> testItems = Collections.singletonList(
                    StoreBasicInfo.create(1L)
            );

            given(categoryQueryService.getCategory(anyLong())).willReturn(testCategory);
            given(userQueryService.getUserById(anyLong())).willReturn(testUser);
            given(tagQueryService.getStoreInfoWithLimitLike(any(), any())).willReturn(testItems);

            //when
            List<StoreBasicInfo> result = myPageService.getMyLike(categoryId, userId);

            // then
            assertThat(result).isNotNull();
            assertThat(result.get(0).getStoreId()).isEqualTo(storeId);

            then(categoryQueryService).should().getCategory(categoryId);
            then(userQueryService).should().getUserById(userId);
            then(tagQueryService).should().getStoreInfoWithLimitLike(testCategory, testUser);
        }
    }

}