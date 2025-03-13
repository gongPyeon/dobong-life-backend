package dobong.life.base.store.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Validated
public class ReviewDTO {
    private Long reviewId;
    private String userName;
    private LocalDateTime reviewDate;
    private String reviewContent;
    private List<String> selectedKeywords;
    private boolean likeByUser;
    private int likeCount;
}
