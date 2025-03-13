package dobong.life.base.review;

import dobong.life.base.store.Domain;
import dobong.life.base.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Middle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review;

    @ManyToOne
    @JoinColumn(name = "keyword_id")
    private Keyword keyword;

    public Middle(Review review, Keyword keyword) {
        this.review = review;
        this.keyword = keyword;
    }
}
