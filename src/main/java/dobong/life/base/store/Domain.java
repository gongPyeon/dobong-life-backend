package dobong.life.base.store;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Builder
public class Domain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String imgUrl;
    private String mapUrl;
    private String address;
    private LocalTime startTime;
    private LocalTime endTime;
    private String day;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;

    public Domain(Long id, String name, Category category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }
}
