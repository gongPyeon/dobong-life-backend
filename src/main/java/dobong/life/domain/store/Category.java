package dobong.life.domain.store;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ParentCategoryType parentCategoryType;

    public static Category create(Long id) { // test용
        return Category.builder()
                .id(id)
                .parentCategoryType(ParentCategoryType.음식)
                .build();
    }
}
