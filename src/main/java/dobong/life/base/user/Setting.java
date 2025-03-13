package dobong.life.base.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Setting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String businessEmail;
    private String developEmail;

    @Column(columnDefinition = "TEXT")
    private String contactUs;

    @Column(columnDefinition = "TEXT")
    private String termsOfService;

    @Column(columnDefinition = "TEXT")
    private String privacyPolicy;

    @Column(columnDefinition = "TEXT")
    private String openSourceLicenses;
}
