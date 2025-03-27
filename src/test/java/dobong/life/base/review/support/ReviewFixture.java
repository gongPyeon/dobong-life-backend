package dobong.life.base.review.support;

import dobong.life.base.review.Review;
import dobong.life.base.review.controller.request.ReviewReqDTO;
import dobong.life.base.store.support.StoreFixture;

import java.util.Arrays;

public class ReviewFixture {
    public static ReviewReqDTO reviewReqDTO() {
        return new ReviewReqDTO(1L, 0.0, Arrays.asList(""), "내용의 사이즈가 몇일까요", "이미지");
    }
}
