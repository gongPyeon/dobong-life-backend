package dobong.life.domain.store;

import dobong.life.domain.review.Review;
import dobong.life.domain.review.ReviewTag;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MiddleTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review;

    @ManyToOne
    @JoinColumn(name = "reviewTag_id")
    private ReviewTag reviewTag;

    public MiddleTag(Review review, ReviewTag reviewTag) {
        this.review = review;
        this.reviewTag = reviewTag;
    }
}
