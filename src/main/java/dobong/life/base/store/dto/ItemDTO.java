package dobong.life.base.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class ItemDTO {
    private String name;
    private String address;
    private List<String> categories;
    private boolean like;
}
