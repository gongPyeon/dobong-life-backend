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
    private CategoryQueryService categoryQueryService;
    private HashTagQueryService hashTagQueryService;
    private DomainQueryService domainQueryService;
    private MiddleQueryService middleQueryService;
    private UserQueryService userQueryService;

    public StoresResDTO getStoreList(Long userId){
        List<Category> categories = categoryQueryService.getAllCategory();
        List<StoresDTO> storesDTOS = getStoresDTOList(categories, userId);
        
        return new StoresResDTO(storesDTOS);
    }

    private List<StoresDTO> getStoresDTOList(List<Category> categories, Long userId) {
        List<StoresDTO> storesDTOList = new ArrayList<>();

        for(Category category : categories){
            Long categoryId = category.getId();
            String categoryName = category.getCategoryName();
            List<ItemDTO> itemDTOList = getItemDTOList(categoryId, userId);

            storesDTOList.add(new StoresDTO(categoryId, categoryName, itemDTOList));
        }
        return storesDTOList;
    }

    private List<ItemDTO> getItemDTOList(Long categoryId, Long userId) {
        List<Domain> domains = domainQueryService.findByCategoryId(categoryId);
        return getItemDTOS(userId, domains);
    }

    public StoresByIdResDTO getStoreListByCategory(Long categoryId, Long userId) {
        Category category = categoryQueryService.getCategory(categoryId);
        String categoryName = category.getCategoryName();
        List<HashTagDTO> hashTagDTOList = getHashTagDTOList(categoryId, userId);

        return new StoresByIdResDTO(categoryId, categoryName, hashTagDTOList);
    }

    private List<HashTagDTO> getHashTagDTOList(Long categoryId, Long userId) {
        List<Tag> tags = hashTagQueryService.getAllTag();
        List<HashTagDTO> hashTagDTOList = new ArrayList<>();

        for(Tag tag : tags){
            List<ItemDTO> itemDTOList = getItemDTOList(categoryId, userId);
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
