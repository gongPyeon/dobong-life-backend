package dobong.life.base.store.service;

import dobong.life.base.review.Review;
import dobong.life.base.review.service.query.MiddleQueryService;
import dobong.life.base.review.service.query.ReviewQueryService;
import dobong.life.base.store.Domain;
import dobong.life.base.store.controller.response.StoreResDTO;
import dobong.life.base.store.controller.response.StoresResDTO;
import dobong.life.base.store.exception.CategoryNotFoundException;
import dobong.life.base.store.exception.DomainNotFoundException;
import dobong.life.base.store.service.query.CategoryQueryService;
import dobong.life.base.store.service.query.DomainQueryService;
import dobong.life.base.store.support.StoreFixture;
import dobong.life.global.util.response.status.BaseErrorCode;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class StoreAndReviewServiceTest {
    @InjectMocks
    private StoreAndReviewService storeAndReviewService;
    @Mock
    private MiddleQueryService middleQueryService;
    private DomainQueryService domainQueryService;
    private ReviewQueryService reviewQueryService;
    private CategoryQueryService categoryQueryService;

    @Nested
    @DisplayName("상점 상세 페이지 조회 서비스 실행 시 ")
    class GetStoreTest{
        @Test
        @DisplayName(":성공")
        void getStore_success(){
            // given
            Long storeId = 1L;
            Long userId = 1L;
            Domain testDomain = StoreFixture.domain();
            List<Review> testReveiwList = StoreFixture.reviewList();
            given(domainQueryService.findById(storeId)).willReturn(testDomain); // 이게 NULL인 이유?
            given(reviewQueryService.findByDomain(any())).willReturn(testReveiwList);

            // when
            StoreResDTO result = storeAndReviewService.getStore(storeId, userId);

            // then
            assertThat(result).isNotNull();
            assertThat(result.getItemDetailDTO().getName()).isEqualTo(testReveiwList.get(0).getUser().getNickName());
            assertThat(result.getReviewsDTO().getReviewDTOList().size()).isEqualTo(1);
        }
    }

}