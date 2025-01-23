package dobong.life.dto.info;

import jdk.jshell.Snippet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@Builder
public class TagInfo {
    private Long tagId;
    private String parentTagName; // tagCategory
    private String subTagName; // hash
}
