package dobong.life.dto.info;

import dobong.life.entity.Domain;
import dobong.life.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class TagGroup {
    private final Tag tag;
    private final List<Domain> domains;
}
