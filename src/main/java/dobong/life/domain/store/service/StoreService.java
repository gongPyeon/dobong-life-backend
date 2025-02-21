package dobong.life.domain.store.service;

import dobong.life.domain.store.Category;
import dobong.life.domain.store.Domain;
import dobong.life.domain.store.controller.response.StoreItemResDto;
import dobong.life.domain.store.controller.response.StoresFilterResDto;
import dobong.life.domain.store.controller.response.StoresResDto;
import dobong.life.domain.user.User;
import dobong.life.domain.store.dto.StoreBasicInfo;
import dobong.life.domain.store.dto.ItemInfo;
import dobong.life.domain.store.dto.StoreDetailInfo;
import dobong.life.domain.store.service.query.CategoryQueryService;
import dobong.life.domain.store.service.query.StoreQueryService;
import dobong.life.domain.store.service.query.TagQueryService;
import dobong.life.domain.user.service.query.UserQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StoreService {

    private final StoreQueryService storeQueryService;
    private final CategoryQueryService categoryQueryService;
    private final UserQueryService userQueryService;
    private final TagQueryService tagQueryService;

    //TODO: 페이징 처리
    public StoresResDto getStoreList(Long categoryId, Long userId){
        Category category = categoryQueryService.getCategory(categoryId);
        User user = userQueryService.getUserById(userId);
        List<ItemInfo> items = tagQueryService.getItemInfos(category, user);

        return new StoresResDto(categoryId, items);
    }

    public StoresResDto getStoreListByQuery(Long categoryId, Long userId, String query) {
        Category category =  categoryQueryService.getCategory(categoryId);
        User user = userQueryService.getUserById(userId);
        List<ItemInfo> items = tagQueryService.getItemInfosByQuery(category, user, query);

        return new StoresResDto(categoryId, items);
    }

    public StoresFilterResDto getStoreListByFilter(Long categoryId, Long userId, List<String> categoryNames, List<Long> subTagIds) {
        User user = userQueryService.getUserById(userId);
        List<StoreBasicInfo> items = tagQueryService.mapToStoreInfosByFilter(user, categoryNames, subTagIds);
        List<String> subTagNames = tagQueryService.getSubTagNames(subTagIds);

        return new StoresFilterResDto(categoryId, categoryNames, subTagNames, items);
    }

    public StoresResDto getStoreListAll(Long categoryId, Long userId, Long tagId, Long subTagId) {
        Category category = categoryQueryService.getCategory(categoryId);
        User user = userQueryService.getUserById(userId);
        List<ItemInfo> items = tagQueryService.getItemInfosMore(category, user, tagId, subTagId);

        return new StoresResDto(categoryId, items);
    }

    public StoreItemResDto getStore(Long categoryId, Long userId, Long storeId) {
        User user = userQueryService.getUserById(userId);
        Domain domain = storeQueryService.getDomain(storeId);
        StoreBasicInfo storeBasicInfo = buildStoreBasicInfo(domain, user);
        StoreDetailInfo storeDetailInfo = buildStoreDetailInfo(domain);

        return new StoreItemResDto(categoryId, storeBasicInfo, storeDetailInfo);
    }

    private StoreBasicInfo buildStoreBasicInfo(Domain domain, User user) {
        return tagQueryService.mapToStoreInfo(domain, user);
    }

    private StoreDetailInfo buildStoreDetailInfo(Domain domain) {
        String subCategoryName = storeQueryService.getSubCategory(domain);
        List<String> menus = storeQueryService.getMenus(domain);
        List<String> keywords = storeQueryService.getHashTags(domain);
        return tagQueryService.mapToStoreDetailInfo(subCategoryName, domain.getAddressDetail(), menus, keywords);
    }
}
