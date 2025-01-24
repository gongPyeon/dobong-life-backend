package dobong.life.dto.info;

import lombok.Getter;

import java.util.List;

@Getter
public class StoreDetailGroup {
    private String subCategory;
    private List<String> keywords;
    private List<String> menus;
}
