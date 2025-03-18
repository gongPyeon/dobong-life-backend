package dobong.life.base.store.service;

import dobong.life.base.review.Review;
import dobong.life.base.review.service.query.KeywordQueryService;
import dobong.life.base.review.service.query.MiddleQueryService;
import dobong.life.base.store.Category;
import dobong.life.base.store.Domain;
import dobong.life.base.store.Tag;
import dobong.life.base.store.controller.response.*;
import dobong.life.base.store.dto.*;
import dobong.life.base.store.service.query.CategoryQueryService;
import dobong.life.base.store.service.query.DomainQueryService;
import dobong.life.base.store.service.query.HashTagQueryService;
import dobong.life.base.user.User;
import dobong.life.base.user.service.query.UserQueryService;
import dobong.life.global.util.constant.DEFINE;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Store;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StoreService {
    private final CategoryQueryService categoryQueryService;
    private final HashTagQueryService hashTagQueryService;
    private final DomainQueryService domainQueryService;
    private final MiddleQueryService middleQueryService;
    private final UserQueryService userQueryService;

    private static final int CATEGORY_MAX = 8;
    private static final int ITEM_MAX = 3;
    private static final int PAGE_SIZE = 0;

    public StoresResDTO getStoreList(Long userId){
        List<String> categories = categoryQueryService.getAllCategory();
        List<StoresDTO> storesDTOS = getStoresDTOList(categories, userId);
        
        return new StoresResDTO(storesDTOS);
    }

    private List<StoresDTO> getStoresDTOList(List<String> categories, Long userId) {
        List<StoresDTO> storesDTOList = new ArrayList<>();

        for(String categoryName : categories){
            List<ItemDTO> itemDTOList = getItemDTOListByCategory(categoryName, userId, ITEM_MAX, PAGE_SIZE);

            storesDTOList.add(new StoresDTO(categoryName, itemDTOList));
        }
        return storesDTOList;
    }

    private List<ItemDTO> getItemDTOListByCategory(String categoryName, Long userId, int max, int page) {
        List<Domain> domains = domainQueryService.findByCategoryName(categoryName, max, page);
        return getItemDTOS(userId, domains);
    }

    private List<ItemDTO> getItemDTOListByHashTag(String hashTag, Long userId) {
        List<Domain> domains = domainQueryService.findByHashTag(hashTag);
        return getItemDTOS(userId, domains);
    }

    public StoresByIdResDTO getStoreListByCategory(String categoryName, Long userId, int page) {
        List<HashTagDTO> hashTagDTOList = getHashTagDTOList(categoryName, userId);
        List<ItemDTO> etcList = getItemDTOListByCategory(categoryName, userId, CATEGORY_MAX, page);
        int pageSize = domainQueryService.getPageSize(categoryName);

        return new StoresByIdResDTO(page, pageSize, categoryName, hashTagDTOList, etcList);
    }

    private List<HashTagDTO> getHashTagDTOList(String categoryName, Long userId) {
        List<Tag> tags = hashTagQueryService.getAllTag(categoryName);
        List<HashTagDTO> hashTagDTOList = new ArrayList<>();

        for(Tag tag : tags){
            List<ItemDTO> itemDTOList = getItemDTOListByHashTag(tag.getHashtagName(), userId);
            hashTagDTOList.add(new HashTagDTO(tag.getHashtagName(), itemDTOList));
        }
        return hashTagDTOList;
    }

    public StoresByQueryResDTO searchStoreList(Long userId, String query, List<String> filter) {
        List<ItemDTO> itemDTOList = getItemDTOListByQuery(userId, query, filter);
        return new StoresByQueryResDTO(filter, query, itemDTOList);
    }

    private List<ItemDTO> getItemDTOListByQuery(Long userId, String query, List<String> filter) {
        List<Domain> domains = domainQueryService.findByQueryAndFilter(query, filter);
        return getItemDTOS(userId, domains);
    }

    private List<ItemDTO> getItemDTOS(Long userId, List<Domain> domains) {
        List<ItemDTO> itemDTOList = new ArrayList<>();

        for(Domain domain : domains){
            String name = domain.getName();
            String address = domain.getAddress();
            Category categoryByDomain = domain.getCategory();
            List<String> categories = categoryQueryService.getCategories(categoryByDomain);
            boolean like = domainQueryService.getLikeByUser(domain, userId);

            itemDTOList.add(new ItemDTO(name, address, categories, like));
        }

        return itemDTOList;
    }

    public StoreLikeResDTO getStoreLikeList(Long userId){
        List<ItemDTO> itemDTOList = getItemDTOList(userId);
        return new StoreLikeResDTO(itemDTOList);
    }

    private List<ItemDTO> getItemDTOList(Long userId) {
        List<Domain> domains = domainQueryService.findByUserId(userId);
        return getItemDTOS(userId, domains);
    }

    public String updateStoreLikeByUser(Long userId, Long storeId) {
        User user = userQueryService.getUserById(userId);
        Domain domain = domainQueryService.findById(storeId);

        domainQueryService.updateStoreLike(user, domain);

        return DEFINE.LIKE_OK;
    }
}
