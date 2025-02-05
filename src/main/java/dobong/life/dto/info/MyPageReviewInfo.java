package dobong.life.dto.info;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class MyPageReviewInfo {
    private Long storeId;
    private String name;
    private List<String> selectedKeywords;
    private String reviewContent;
}
