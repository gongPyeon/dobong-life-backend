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

    @ManyToOne
    @JoinColumn(name = "domain_id")
    private Domain domain;

    public Middle(Review review, Keyword keyword, Domain domain) {
        this.review = review;
        this.keyword = keyword;
        this.domain = domain;
    }
}
