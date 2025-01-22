package dobong.life.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class StoreListMoreResponseDto {
    private Long categoryId;
    private Result result;

    @NoArgsConstructor
    @Getter
    public class Result{
        private Long tagId;
        private String tagCategory;
        private String hashTag;
        private List<Content> contents;
    }

    @NoArgsConstructor
    @Getter
    public class Content{
        private Long storeId;
        private String storeName;
        private String storeLocation;
        private String imgUrl;
        private boolean storeLike;
    }
}
