package dobong.life.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;
    private int likeCount;
    private LocalDateTime date;
    private Double score;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "domain_id")
    private Domain domain;

    public Review(String content, LocalDateTime date, User user, Domain domain) {
        this.content = content;
        this.likeCount = 0;
        this.date = date;
        this.score = 0.0;
        this.user = user;
        this.domain = domain;
    }

    public void updateLikeCount(){
        this.likeCount++;
    }

    public static Review create(Long id, User user, Domain domain){
        return Review.builder()
                .id(id)
                .content("내용")
                .domain(domain)
                .date(LocalDateTime.now())
                .likeCount(0)
                .score(0.0)
                .user(user)
                .build();
    }
}
