package dobong.life.service;

import dobong.life.dto.info.ItemInfo;
import dobong.life.dto.info.StoreBasicInfo;
import dobong.life.dto.info.StoreDetailInfo;
import dobong.life.entity.Domain;
import dobong.life.entity.SubTag;
import dobong.life.entity.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StoreMapper {

    public ItemInfo buildItemInfo(Tag tag, SubTag subTag, List<StoreBasicInfo> stores) {
        if(stores.isEmpty()) return null;
        return new ItemInfo(tag.getId(), tag.getParentTagName(), subTag.getId(), subTag.getSubTagName(), stores);
    }

    public StoreBasicInfo buildStoreBasicInfo(Domain domain, boolean like) {
        return new StoreBasicInfo(domain.getId(), domain.getName(), domain.getAddressDong(), domain.getImageUrl(), like);
    }

    public StoreDetailInfo buildStoreDetailInfo(String subTagName, String location, List<String> menus, List<String> keywords) {
        return new StoreDetailInfo(subTagName, location, menus, keywords);
    }
}
