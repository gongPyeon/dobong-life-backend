package dobong.life.dto.info;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class ReviewDetailInfo {
    private String userName;
    private int userReviewCount;
    private LocalDateTime reviewDate;
    private String reviewContent;
    private List<String> selectedKeywords;
    private boolean likedByUser;
    private int likeCount;
}
