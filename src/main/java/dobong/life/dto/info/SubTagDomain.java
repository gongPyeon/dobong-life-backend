package dobong.life.dto.info;


import dobong.life.entity.Domain;
import dobong.life.entity.SubTag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@AllArgsConstructor
@ToString
public class SubTagDomain {
    private final SubTag subTag;
    private final List<Domain> domains;
}
