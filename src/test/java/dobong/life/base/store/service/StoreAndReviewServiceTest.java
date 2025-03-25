package dobong.life.base.store.service;

import dobong.life.base.review.service.query.MiddleQueryService;
import dobong.life.base.review.service.query.ReviewQueryService;
import dobong.life.base.store.service.query.CategoryQueryService;
import dobong.life.base.store.service.query.DomainQueryService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StoreAndReviewServiceTest {
    @InjectMocks
    private StoreAndReviewService storeAndReviewService;
    @Mock
    private MiddleQueryService middleQueryService;
    private DomainQueryService domainQueryService;
    private ReviewQueryService reviewQueryService;
    private CategoryQueryService categoryQueryService;

}