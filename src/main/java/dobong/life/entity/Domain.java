package dobong.life.entity;

import jakarta.persistence.*;
import lombok.*;

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

    private String imageUrl;
    private String mapUrl;
    private String addressDetail;
    private String addressDong;
    private String itemName;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


    public static Domain create(Long storeId){
        return Domain.builder()
                .id(storeId)
                .name("순대집")
                .imageUrl("이미지")
                .mapUrl("지도")
                .addressDetail("서울시 도봉동")
                .addressDong("도봉동")
                .itemName("순대1,순대2")
                .build();
    }
}
