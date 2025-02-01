package dobong.life.dto.info;

import dobong.life.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ReviewDetails {
    private String userName;
    private int userReviewCount;
    private LocalDateTime reviewDate;
    private String reviewContent;
    private List<String> selectedKeywords;
    private boolean likedByUser;
    private int likeCount;
}
