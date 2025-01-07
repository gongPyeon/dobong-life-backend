package dobong.life.crolling;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
public class TestEntity {
    String title;
    String posterUrl;
    List<String> menuItems;
    List<String> tagItems;
    String mainCategory = "";
    String subCategory = "";
    String address;

    public TestEntity(String title, String posterUrl, List<String> menuItems, List<String> tagItems, String address) {
        this.title = title;
        this.posterUrl = posterUrl;
        this.menuItems = menuItems;
        this.tagItems = tagItems;
        this.address = address;
    }

    @Override
    public String toString() {
        return "TestEntity{" +
                "title='" + title + '\'' +
                ", posterUrl='" + posterUrl + '\'' +
                ", menuItems=" + menuItems +
                ", tagItems=" + tagItems +
                '}';
    }
}
