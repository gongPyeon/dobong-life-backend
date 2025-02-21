package dobong.life.domain.user.controller.response;

import dobong.life.domain.review.controller.request.MyPageReviewInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class MyPageReviewResDto {
    private List<MyPageReviewInfo> reviews;
}
