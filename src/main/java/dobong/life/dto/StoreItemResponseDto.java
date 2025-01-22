package dobong.life.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class StoreItemResponseDto {
    private Long categoryId;
    private Result result;

    @Getter
    @NoArgsConstructor
    public class Result{
        private String subCategory;
        private String imgUrl;
        private String storeName;
        private String storeLocationDetail;
        private String storeLocation;
        private int storeLike;
        private List<Menu> storeMenu;
        private List<Keyword> storeKeyword;
    }

    @Getter
    @NoArgsConstructor
    public class Menu{
        private String name;
    }

    @Getter
    @NoArgsConstructor
    public class Keyword{
        private String name;
    }
}
