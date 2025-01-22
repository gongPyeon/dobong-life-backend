package dobong.life.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class StoreByFilterResponseDto {
    private Long categoryId;
    private List<Result> results;

    @NoArgsConstructor
    @Getter
    public class Result{
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
