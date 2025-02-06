package dobong.life.dto;

import dobong.life.dto.info.MyPageReviewInfo;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class MyPageReviewResDto {
    private List<MyPageReviewInfo> reviews;
}
