package dobong.life.base.store.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Validated
public class ItemDetailDTO {
    private String name;
    private String address;
    private List<String> categories;
    private LocalTime startTime;
    private LocalTime endTime;
    private String day;
    private String description;
    private boolean like;
}
