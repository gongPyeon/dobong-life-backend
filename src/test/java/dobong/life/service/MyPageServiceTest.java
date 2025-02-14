package dobong.life.service;

import dobong.life.dto.MyPageResDto;
import dobong.life.entity.Category;
import dobong.life.entity.User;
import dobong.life.service.query.*;
import dobong.life.util.DEFINE;
import org.aspectj.lang.annotation.Before;
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
            Long userId = 2L;
            int reviewCount = 1;
            int reviewLikeCount = 1;
            int foodLikeCount = 2;
            int placeLikeCount = 2;
            int businessLikeCount = 2;

            //given(userQueryService.getUserById(anyLong())).willReturn(testUser);
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

}