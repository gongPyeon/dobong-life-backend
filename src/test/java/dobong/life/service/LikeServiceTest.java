package dobong.life.service;

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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("LikeService를_테스트_한다")
class LikeServiceTest {

    @InjectMocks
    private LikeService likeService;

    @Mock
    private UserQueryService userQueryService;

    @Mock
    private StoreQueryService storeQueryService;

    @Mock
    private ReviewQueryService reviewQueryService;

    User testUser;
    Domain testDomain;

    Review testReview;

    @BeforeEach
    void setUp(){
        testUser = User.create(1L);
        testDomain = Domain.create(1L);
        testReview = Review.create(1L, testUser, testDomain);
    }

    @Nested
    @DisplayName("특정 상점에 대한 좋아요 Service 실행 시")
    class UpdateStoreLikeByUser{
        @Test
        @DisplayName("성공")
        void updateStoreLikeByUser_success(){

            // given
            Long userId = 1L;
            Long storeId = 1L;

            given(userQueryService.getUserById(anyLong())).willReturn(testUser);
            given(storeQueryService.getDomain(anyLong())).willReturn(testDomain);
            willDoNothing().given(storeQueryService).updateStoreLike(any(), any());

            // when
            String result = likeService.updateStoreLikeByUser(userId, storeId);

            // then
            assertThat(result).isEqualTo(DEFINE.LIKE_OK);

            then(userQueryService).should().getUserById(userId);
            then(storeQueryService).should().getDomain(storeId);
            then(storeQueryService).should().updateStoreLike(testUser, testDomain);

        }
    }

    @Nested
    @DisplayName("특정 리뷰에 대한 좋아요 Service 실행 시")
    class UpdateReviewLikeByUser{
        @Test
        @DisplayName("성공")
        void updateReviewLikeByUser_success(){

            // given
            Long userId = 1L;
            Long reviewId = 1L;

            given(userQueryService.getUserById(anyLong())).willReturn(testUser);
            given(reviewQueryService.getReviewById(anyLong())).willReturn(testReview);
            willDoNothing().given(storeQueryService).updateStoreLike(any(), any());

            // when
            String result = likeService.updateReviewLikeByUser(userId, reviewId);

            // then
            assertThat(result).isEqualTo(DEFINE.LIKE_OK);

            then(userQueryService).should().getUserById(userId);
            then(reviewQueryService).should().getReviewById(reviewId);
            then(storeQueryService).should().updateReviewLike(testUser, testReview);

        }
    }

}