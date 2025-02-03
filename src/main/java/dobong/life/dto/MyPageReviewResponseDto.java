package dobong.life.dto;

import dobong.life.dto.info.MyPageReviewInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class MyPageReviewResponseDto {
    private List<MyPageReviewInfo> results;
}
