package dobong.life.service;

import dobong.life.dto.info.MyPageReviewInfo;
import dobong.life.entity.Domain;
import dobong.life.entity.Review;
import dobong.life.entity.User;
import dobong.life.service.query.ReviewQueryService;
import dobong.life.service.query.StoreQueryService;
import dobong.life.service.query.UserQueryService;
import dobong.life.util.DEFINE;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
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

}