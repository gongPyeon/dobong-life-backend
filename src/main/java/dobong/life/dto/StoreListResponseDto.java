package dobong.life.dto;

import dobong.life.dto.info.StoreBasicInfo;
import dobong.life.dto.info.TagInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class StoreListResponseDto {
    private Long categoryId;
    private List<Result> results;

    @NoArgsConstructor
    @Getter
    public static class Result{
        private TagInfo tag;
        private List<StoreBasicInfo> contents;
    }
}
