package dobong.life.controller;

import dobong.life.dto.StoresResDto;
import dobong.life.dto.info.ItemInfo;
import dobong.life.dto.info.StoreBasicInfo;

import java.util.ArrayList;
import java.util.List;

public class TestResponse {

    public static StoresResDto makeTestGetStoresResDto(
            Long categoryId,
            Long tagId,
            String parentTagName,
            Long subTagId,
            String subTagName,
            Long[] storeIds, // 여러 음식점 ID를 배열로 받음
            String[] storeNames, // 여러 음식점 이름을 배열로 받음
            String[] storeLocations, // 여러 음식점 위치를 배열로 받음
            String[] imgUrls, // 여러 음식점 이미지 URL을 배열로 받음
            boolean[] storeLikes // 여러 음식점 like 여부를 배열로 받음
    ) {
        List<StoreBasicInfo> stores = new ArrayList<>();

        // 여러 개의 StoreBasicInfo 객체 생성
        for (int i = 0; i < storeIds.length; i++) {
            StoreBasicInfo store = new StoreBasicInfo(
                    storeIds[i],
                    storeNames[i],
                    storeLocations[i],
                    imgUrls[i],
                    storeLikes[i]
            );
            stores.add(store);
        }

        // ItemInfo 생성
        List<ItemInfo> itemInfos = new ArrayList<>();
        itemInfos.add(new ItemInfo(tagId, parentTagName, subTagId, subTagName, stores));

        // StoresResDto 리턴
        return new StoresResDto(categoryId, itemInfos);
    }

}
