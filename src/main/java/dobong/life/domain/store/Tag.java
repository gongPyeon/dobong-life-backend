package dobong.life.domain.store;

import dobong.life.domain.store.Category;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String parentTagName; // 일상의 작은 행복

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
