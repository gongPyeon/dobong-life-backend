package dobong.life.dto.info;

import dobong.life.enums.ReviewTagType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class RatingDetails{
    private String reviewTag;
    private int ratingTag;
}
