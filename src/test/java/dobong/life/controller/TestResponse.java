package dobong.life.controller;

import dobong.life.dto.StoreItemResDto;
import dobong.life.dto.StoresFilterResDto;
import dobong.life.dto.StoresResDto;
import dobong.life.dto.info.ItemInfo;
import dobong.life.dto.info.StoreBasicInfo;
import dobong.life.dto.info.StoreDetailInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestResponse {

    public static StoresResDto makeTestGetStoresResDto(Long categoryId, Long tagId, String parentTagName, Long subTagId,
                                                       String subTagName, Long[] storeIds, String[] storeNames,
                                                       String[] storeLocations, String[] imgUrls, boolean[] storeLikes) {
        List<StoreBasicInfo> stores = new ArrayList<>();

        // 여러 개의 StoreBasicInfo 객체 생성
        for (int i = 0; i < storeIds.length; i++) {
            StoreBasicInfo store = new StoreBasicInfo(
                    storeIds[i], storeNames[i], storeLocations[i], imgUrls[i], storeLikes[i]);
            stores.add(store);
        }

        // ItemInfo 생성
        List<ItemInfo> itemInfos = new ArrayList<>();
        itemInfos.add(new ItemInfo( tagId, parentTagName, subTagId, subTagName, stores));

        // StoresResDto 리턴
        return new StoresResDto(categoryId, itemInfos);
    }

    public static StoresFilterResDto makeTestGetStoresFilterResDto(Long categoryId, String[] categoryNames, String[] subTagNames,
                                                                    Long[] storeIds, String[] storeNames, String[] storeLocations,
                                                                    String[] imgUrls, boolean[] storeLikes) {
        List<StoreBasicInfo> stores = new ArrayList<>();

        // 여러 개의 StoreBasicInfo 객체 생성
        for (int i = 0; i < storeIds.length; i++) {
            StoreBasicInfo store = new StoreBasicInfo( storeIds[i], storeNames[i], storeLocations[i], imgUrls[i], storeLikes[i]);
            stores.add(store);
        }

        // StoresResDto 리턴
        return new StoresFilterResDto(categoryId, Arrays.asList(categoryNames),
                Arrays.asList(subTagNames), stores);

    }

    public static StoreItemResDto makeTestGetStoreItemResDto(Long categoryId, Long storeId, String storeName,
                                                             String storeLocation, String imgUrl, boolean storeLike,
                                                             String subCategoryName, String storeLocationDetail,
                                                             String[] storeMenus, String[] storeKeywords){

        StoreBasicInfo storeBasicInfo = new StoreBasicInfo(storeId, storeName, storeLocation, imgUrl, storeLike);

        StoreDetailInfo storeDetailInfo = new StoreDetailInfo(subCategoryName, storeLocationDetail,
                Arrays.asList(storeMenus), Arrays.asList(storeKeywords));

        return new StoreItemResDto(categoryId, storeBasicInfo, storeDetailInfo);

    }

}
