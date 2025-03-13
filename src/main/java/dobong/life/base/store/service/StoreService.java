package dobong.life.base.store.service;

import dobong.life.base.review.Review;
import dobong.life.base.review.service.query.KeywordQueryService;
import dobong.life.base.review.service.query.MiddleQueryService;
import dobong.life.base.store.Category;
import dobong.life.base.store.Domain;
import dobong.life.base.store.Tag;
import dobong.life.base.store.controller.response.StoreResDTO;
import dobong.life.base.store.controller.response.StoresByIdResDTO;
import dobong.life.base.store.controller.response.StoresByQueryResDTO;
import dobong.life.base.store.controller.response.StoresResDTO;
import dobong.life.base.store.dto.*;
import dobong.life.base.store.service.query.CategoryQueryService;
import dobong.life.base.store.service.query.DomainQueryService;
import dobong.life.base.store.service.query.HashTagQueryService;
import dobong.life.domain.user.service.query.UserQueryService;
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
        return getItemDTOS(userId, itemDTOList, domains);
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
        List<ItemDTO> itemDTOList = new ArrayList<>();

        List<Domain> domains = domainQueryService.findByQueryAndFilter(query, filter);
        return getItemDTOS(userId, itemDTOList, domains);
    }

    private List<ItemDTO> getItemDTOS(Long userId, List<ItemDTO> itemDTOList, List<Domain> domains) {
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

    public StoreResDTO getStore(Long userId, Long storeId) {
        ItemDTO itemDTO = getItemDTO(userId, storeId);
        ReviewsDTO reviewsDTO = getReviewsDTO();

        return new StoreResDTO(itemDTO, reviewsDTO);
    }

    // TODO : 리뷰와 가게 정보를 같이 반환할때
    private ReviewsDTO getReviewsDTO() {
        List<String> keywords = middleQueryService.findAllBy(); // 정렬
        double averageRating = calculateRating();
        int reviewCount = 0; // 리뷰개수
        List<ReviewDTO> reviewDTOList = getReviewDTO();
        return new ReviewsDTO(keywords, averageRating, reviewCount, reviewDTOList);
    }

    private List<ReviewDTO> getReviewDTO() {
        List<ReviewDTO> reviewDTOList = new ArrayList<>();

        List<Review> reviews;

        for(Review review : reviews){
            Long reviewId;
            String userName;
            LocalDateTime reviewDate;
            String reviewContent;
            List<String> selectedKeywords;
            boolean likeByUser;
            int likeCount;
            reviewDTOList.add(new ReviewDTO(reviewId, userName, reviewDate, reviewContent, selectedKeywords, likeByUser, likeCount));
        }
        return reviewDTOList;
    }

    private ItemDTO getItemDTO(Long userId, Long storeId){
        Domain domain = domainQueryService.findById(storeId);
        Category categoryByDomain = domain.getCategory();
        List<String> categories = categoryQueryService.getCategories(categoryByDomain);
        boolean like = domainQueryService.getLikeByUser(domain, userId);

        return new ItemDTO(domain.getName(), domain.getAddress(), categories, like);
    }
}
