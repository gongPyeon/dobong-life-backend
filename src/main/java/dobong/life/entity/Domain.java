package dobong.life.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
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
    @JoinColumn(name = "domain_id")
    private Category category;

}
