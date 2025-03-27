package dobong.life.base.review.service;

import dobong.life.base.review.Review;
import dobong.life.base.review.controller.request.ReviewReqDTO;
import dobong.life.base.review.exception.ReviewNotFoundException;
import dobong.life.base.review.service.query.KeywordQueryService;
import dobong.life.base.review.service.query.MiddleQueryService;
import dobong.life.base.review.service.query.ReviewQueryService;
import dobong.life.base.review.support.ReviewFixture;
import dobong.life.base.store.Domain;
import dobong.life.base.store.controller.response.StoresResDTO;
import dobong.life.base.store.exception.CategoryNotFoundException;
import dobong.life.base.store.exception.DomainNotFoundException;
import dobong.life.base.store.service.StoreService;
import dobong.life.base.store.service.query.CategoryQueryService;
import dobong.life.base.store.service.query.DomainQueryService;
import dobong.life.base.store.service.query.HashTagQueryService;
import dobong.life.base.store.support.StoreFixture;
import dobong.life.base.user.User;
import dobong.life.base.user.exception.UserNotFoundException;
import dobong.life.base.user.service.query.UserQueryService;
import dobong.life.global.auth.support.AuthFixture;
import dobong.life.global.util.response.status.BaseCode;
import dobong.life.global.util.response.status.BaseErrorCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ReviewServiceTest {
    @InjectMocks
    private ReviewService reviewService;
    @Mock
    private UserQueryService userQueryService;
    @Mock
    private DomainQueryService domainQueryService;
    @Mock
    private ReviewQueryService reviewQueryService;
    @Mock
    private MiddleQueryService middleQueryService;
    @Mock
    private KeywordQueryService keywordQueryService;
    private List<Domain> testStoreDTOList;
    private Long userId = 1L;
    private Long storeId = 1L;
    private Long reviewId = 1L;
    private User testUser;
    private Domain testDomain;
    private Review testReview;
    private ReviewReqDTO reqDTO;

    @BeforeEach
    void setUp(){
        testDomain = StoreFixture.domain();
        testUser = AuthFixture.user();
        reqDTO = ReviewFixture.reviewReqDTO();
        testReview = StoreFixture.review();
    }

    @Nested
    @DisplayName("리뷰 저장 서비스 실행 시 ")
    class saveReviewTest{
        @Test
        @DisplayName(":성공")
        void saveReview_success(){
            // given
            given(userQueryService.getUserById(userId)).willReturn(testUser);
            given(domainQueryService.findById(storeId)).willReturn(testDomain);

            // when
            String result = reviewService.saveReview(reqDTO, userId);

            // then
            assertThat(result).isNotNull();
            assertThat(result).isEqualTo(BaseCode.SUCCESS_SAVE_REVIEW.getMessage());
        }

        @Test
        @DisplayName("사용자가 존재하지 않을 경우:실패")
        void saveReview_userNotFound(){
            // given
            given(userQueryService.getUserById(userId)).willThrow(new UserNotFoundException(BaseErrorCode.USER_NOT_FOUND,
                    "[ERROR] "+userId+"에 해당하는 사용자를 찾을 수 없습니다"));

            // when & then
            assertThrows(UserNotFoundException.class,
                    () -> reviewService.saveReview(reqDTO, userId));
        }

        @Test
        @DisplayName("상점이 존재하지 않을 경우:실패")
        void saveReview_domainNotFound(){
            // given
            given(domainQueryService.findById(storeId)).willThrow(new DomainNotFoundException(BaseErrorCode.DOMAIN_NOT_FOUND,
                    "[ERROR] 상점 아이디 " + storeId + "를 찾을 수 없습니다"));

            // when & then
            assertThrows(DomainNotFoundException.class,
                    () -> reviewService.saveReview(reqDTO, userId));
        }
    }

    @Nested
    @DisplayName("리뷰 좋아요 서비스 실행 시 ")
    class updateReviewLikeTest{
        @Test
        @DisplayName(":성공")
        void updateReviewLike_success(){
            // given
            given(userQueryService.getUserById(userId)).willReturn(testUser);
            given(reviewQueryService.getReviewById(reviewId)).willReturn(testReview);

            // when
            String result = reviewService.updateReviewLikeByUser(userId, reviewId);

            // then
            assertThat(result).isNotNull();
            assertThat(result).isEqualTo(BaseCode.SUCCESS_UPDATE_LIKE.getMessage());
        }

        @Test
        @DisplayName("사용자가 존재하지 않을 경우:실패")
        void updateReviewLike_userNotFound(){
            // given
            given(userQueryService.getUserById(userId)).willThrow(new UserNotFoundException(BaseErrorCode.USER_NOT_FOUND,
                    "[ERROR] "+userId+"에 해당하는 사용자를 찾을 수 없습니다"));

            // when & then
            assertThrows(UserNotFoundException.class,
                    () -> reviewService.updateReviewLikeByUser(userId, reviewId));
        }

        @Test
        @DisplayName("리뷰가 존재하지 않을 경우:실패")
        void updateReviewLike_domainNotFound(){
            // given
            given(reviewQueryService.getReviewById(reviewId)).willThrow(new ReviewNotFoundException(BaseErrorCode.REVIEW_NOT_FOUND,
                    "[ERROR] "+reviewId+"에 해당하는 리뷰를 찾을 수 없습니다"));

            // when & then
            assertThrows(ReviewNotFoundException.class,
                    () -> reviewService.updateReviewLikeByUser(userId, reviewId));
        }
    }

}