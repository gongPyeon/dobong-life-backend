package dobong.life.service;

import dobong.life.domain.review.controller.response.ReviewResDto;
import dobong.life.domain.review.controller.request.MyPageReviewInfo;
import dobong.life.domain.review.service.ReviewService;
import dobong.life.domain.store.Domain;
import dobong.life.domain.review.Review;
import dobong.life.domain.user.User;
import dobong.life.domain.review.service.query.ReviewQueryService;
import dobong.life.domain.store.service.query.StoreQueryService;
import dobong.life.domain.user.service.query.UserQueryService;
import dobong.life.global.util.constant.DEFINE;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ReviewService를_테스트_한다")
class ReviewServiceTest {

    @InjectMocks
    private ReviewService reviewService;

    @Mock
    private StoreQueryService storeQueryService;

    @Mock
    private UserQueryService userQueryService;

    @Mock
    private ReviewQueryService reviewQueryService;

    User testUser;
    Domain testDomain;
    @BeforeEach
    void setUp(){
        testUser = User.create(1L);
        testDomain = Domain.create(1L);
    }
    @Nested
    @DisplayName("리뷰 저장 Service 실행 시")
    class SaveReview{

        @Test
        @DisplayName("성공")
        void saveReview_success(){
            //given
            Long userId = 1L;
            Long storeId = 1L;
            MyPageReviewInfo reviewInfo = MyPageReviewInfo.create(1L);
            Review testReview = Review.create(1L, testUser, testDomain);

            given(userQueryService.getUserById(anyLong())).willReturn(testUser);
            given(storeQueryService.getDomain(anyLong())).willReturn(testDomain);
            willDoNothing().given(reviewQueryService).saveReview(testReview);
            // willDoNothing().given(reviewQueryService).saveMiddleTag(); middle tag 테스트 보완
            //when private method 때문에 문제가 발생하는 것 같다
            String result = reviewService.saveReview(reviewInfo, userId);

            //then
            assertThat(result).isEqualTo(DEFINE.REVIEW_OK);

            then(userQueryService).should().getUserById(userId);
            then(storeQueryService).should().getDomain(storeId);
            then(reviewQueryService).should().saveReview(testReview);
        }
    }

    @Nested
    @DisplayName("상점의 모든 리뷰 조회 Service 실행 시")
    class getStoreReview{

        @Test
        @DisplayName("성공")
        void getStoreReview_success(){
            //given
            Long categoryId = 1L;
            Long userId = 1L;
            Long storeId = 1L;

            given(userQueryService.getUserById(anyLong())).willReturn(testUser);
            given(storeQueryService.getDomain(anyLong())).willReturn(testDomain);

            //when
            ReviewResDto result = reviewService.getStoreReview(categoryId, userId, storeId);

            //then
            assertThat(result).isNotNull();

            then(userQueryService).should().getUserById(userId);
            then(storeQueryService).should().getDomain(storeId);
        }
    }

}