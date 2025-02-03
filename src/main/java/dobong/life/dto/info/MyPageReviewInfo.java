package dobong.life.dto.info;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class MyPageReviewInfo {
    private long storeId;
    private List<String> selectedKeywords;
    private String reviewContent;
}
