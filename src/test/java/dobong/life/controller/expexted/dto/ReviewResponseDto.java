package dobong.life.controller.expexted.dto;

import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class ReviewResponseDto {
    public static ResultMatcher expectedPostReviewResDto(){
        return result -> {
            MockMvcResultMatchers.jsonPath("$.result").value("리뷰 등록이 완료되었습니다!").match(result);
        };
    }

    public static ResultMatcher expectedGetReviewResDto(){
        return result -> {
            MockMvcResultMatchers.jsonPath("$.result.categoryId").value(1L).match(result);
            MockMvcResultMatchers.jsonPath("$.result.storeId").value(1L).match(result);
            MockMvcResultMatchers.jsonPath("$.result.result.averageRating").value(0.0).match(result);
            MockMvcResultMatchers.jsonPath("$.result.result.ratingCount").value(0).match(result);

            MockMvcResultMatchers.jsonPath("$.result.result.ratingDetails[0].reviewTag").value("good").match(result);
            MockMvcResultMatchers.jsonPath("$.result.result.ratingDetails[0].ratingTag").value(3).match(result);

            MockMvcResultMatchers.jsonPath("$.result.result.reviewDetails[0].reviewId").value(1L).match(result);
            MockMvcResultMatchers.jsonPath("$.result.result.reviewDetails[0].userName").value("test").match(result);
            MockMvcResultMatchers.jsonPath("$.result.result.reviewDetails[0].userReviewCount").value(0).match(result);
            MockMvcResultMatchers.jsonPath("$.result.result.reviewDetails[0].reviewContent").value("테스트 용 리뷰 내용 저장").match(result);
            MockMvcResultMatchers.jsonPath("$.result.result.reviewDetails[0].selectedKeywords[0]").value("test1").match(result);
            MockMvcResultMatchers.jsonPath("$.result.result.reviewDetails[0].selectedKeywords[1]").value("test2").match(result);
            MockMvcResultMatchers.jsonPath("$.result.result.reviewDetails[0].likedByUser").value(true).match(result);
            MockMvcResultMatchers.jsonPath("$.result.result.reviewDetails[0].likeCount").value(1).match(result);
        };
    }
}