package dobong.life.domain.like;

import dobong.life.domain.store.Domain;
import dobong.life.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DomainLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "domain_id")
    private Domain domain;

    public DomainLike(User user, Domain domain) {
        this.user = user;
        this.domain = domain;
    }
}
