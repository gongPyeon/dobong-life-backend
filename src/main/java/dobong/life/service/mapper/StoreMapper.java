package dobong.life.service.mapper;

import dobong.life.dto.info.StoreBasicInfo;
import dobong.life.dto.info.TagInfo;
import dobong.life.entity.Domain;
import dobong.life.entity.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StoreMapper {
    public TagInfo toTagInfo(Tag tag) {
        return TagInfo.builder()
                .tagId(tag.getId())
                .parentTagName(tag.getParentTagName())
                .subTagName(tag.getSubTagName())
                .build();
    }

    public StoreBasicInfo toStoreBasicInfo(Domain domain, boolean isFavorite) {
        return StoreBasicInfo.builder()
                .storeId(domain.getId())
                .storeName(domain.getNameKr())
                .storeLocation(domain.getAddressDong())
                .imgUrl(domain.getImageUrl())
                .storeLike(isFavorite)
                .build();
    }
}
