package dobong.life.domain.like.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LikeCount {

    private int foodLikeCount;
    private int placeLikeCount;
    private int businessLikeCount;
}
