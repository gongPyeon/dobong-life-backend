package dobong.life.base.store.service;

import dobong.life.base.store.Category;
import dobong.life.base.store.Domain;
import dobong.life.base.store.Tag;
import dobong.life.base.store.controller.response.StoresByIdResDTO;
import dobong.life.base.store.controller.response.StoresResDTO;
import dobong.life.base.store.dto.HashTagDTO;
import dobong.life.base.store.dto.ItemDTO;
import dobong.life.base.store.dto.StoresDTO;
import dobong.life.base.store.service.query.CategoryQueryService;
import dobong.life.base.store.service.query.DomainQueryService;
import dobong.life.base.store.service.query.HashTagQueryService;
import dobong.life.domain.user.service.query.UserQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StoreService {
    private CategoryQueryService categoryQueryService;
    private HashTagQueryService hashTagQueryService;
    private DomainQueryService domainQueryService;
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
        List<ItemDTO> itemDTOList = new ArrayList<>();

        List<Domain> domains = domainQueryService.findByCategoryId(categoryId);
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
}
