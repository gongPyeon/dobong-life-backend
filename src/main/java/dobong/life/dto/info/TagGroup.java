package dobong.life.dto.info;

import dobong.life.entity.Domain;
import dobong.life.entity.SubTag;
import dobong.life.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@AllArgsConstructor
@ToString
public class TagGroup {
    private final Tag tag;
    private final List<SubTagDomain> subTagDomains;
}
