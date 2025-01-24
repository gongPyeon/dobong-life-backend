package dobong.life.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Domain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sub_tag_id")
    private SubTag subTag;

    @OneToOne
    @JoinColumn(name = "sub_category_id")
    private SubCategory subCategory;

    private String nameKr;
    private String nameEn;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String imageUrl;
    private int favoriteCount;
    private int reviewCount;
    private String mapUrl;
    private String addressDetail;
    private String addressDong;

}
