package dobong.life.service;

import dobong.life.dto.StoreItemResponseDto;
import dobong.life.dto.StoreListFilterResponseDto;
import dobong.life.dto.StoreListResponseDto;
import dobong.life.dto.StoreReviewResponseDto;
import dobong.life.dto.info.*;
import dobong.life.entity.*;
import dobong.life.service.mapper.StoreMapper;
import dobong.life.service.query.ReviewQueryService;
import dobong.life.service.query.StoreQueryService;
import dobong.life.service.query.TagQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class StoreService {

    private final StoreQueryService storeQueryService;
    private final TagQueryService tagQueryService;
    private final ReviewQueryService reviewQueryService;
    private final StoreMapper storeMapper;

    public StoreListResponseDto getStoreList(Long categoryId, String email){
        Category category = storeQueryService.getCategory(categoryId);
        User user = storeQueryService.getUserByEmail(email);
        List<TagGroup> tagGroups = tagQueryService.getTagGroups(category);

        return StoreListResponseDto.builder()
                .categoryId(categoryId)
                .results(createStoreGroups(tagGroups, user))
                .build();
    }

    private List<StoreGroup> createStoreGroups(List<TagGroup> tagGroups, User user) {
        return tagGroups.stream()
                .flatMap(tagGroup -> createStoreGroupsForTagGroup(tagGroup, user).stream())
                .filter(group -> !group.getStores().isEmpty())
                .collect(Collectors.toList());
    }

    private List<StoreGroup> createStoreGroupsForTagGroup(TagGroup tagGroup, User user) {
        return tagGroup.getSubTagDomains().stream()
                .map(subTagDomain -> StoreGroup.builder()
                        .tag(storeMapper.toTagInfo(tagGroup.getTag(), subTagDomain.getSubTag()))
                        .stores(createStoreList(subTagDomain.getDomains(), user))
                        .build())
                .collect(Collectors.toList());
    }

    private List<StoreBasicInfo> createStoreList(List<Domain> domains, User user) {
        return domains.stream()
                .map(domain -> createStoreInfo(domain, user))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private StoreBasicInfo createStoreInfo(Domain domain, User user) {
        boolean isFavorite = storeQueryService.isUserFavorite(domain, user);
        return storeMapper.toStoreBasicInfo(domain, isFavorite);
    }

    public StoreListResponseDto getStoreList(Long categoryId, String email, String query) {
        Category category = storeQueryService.getCategory(categoryId);
        User user = storeQueryService.getUserByEmail(email);
        List<TagGroup> tagGroups = tagQueryService.getTagGroupsByQuery(category, query);

        return StoreListResponseDto.builder()
                .categoryId(categoryId)
                .results(createStoreGroups(tagGroups, user))
                .build();
    }

    //TODO: filter도 응답형식을 맞춰야할까?
    public StoreListFilterResponseDto getStoreList(Long categoryId, String email, List<String> categoryNames, List<String> subTagNames) {
        User user = storeQueryService.getUserByEmail(email);
        List<Domain> domains = tagQueryService.getTagGroupsFilter(categoryNames, subTagNames);

        return StoreListFilterResponseDto.builder()
                .categoryId(categoryId)
                .categoryNames(categoryNames)
                .subTagNames(subTagNames)
                .results(createStoreList(domains, user))
                .build();
    }

    public StoreListResponseDto getStoreList(Long categoryId, String email, Long tagId, String subTagName) {
        Category category = storeQueryService.getCategory(categoryId);
        User user = storeQueryService.getUserByEmail(email);
        List<TagGroup> tagGroups = tagQueryService.getTagGroupsMore(category, tagId, subTagName);

        return StoreListResponseDto.builder()
                .categoryId(categoryId)
                .results(createStoreGroups(tagGroups, user))
                .build();
    }

    public StoreItemResponseDto getStore(Long categoryId, String email, Long storeId) {
        Category category = storeQueryService.getCategory(categoryId);
        User user = storeQueryService.getUserByEmail(email);
        Domain domain = storeQueryService.getStore(category, storeId);
        String subCategory = domain.getSubCategory().getName();

        return StoreItemResponseDto.builder()
                .categoryId(categoryId)
                .result(createStoreInfoDetail(domain, user, subCategory))
                .build();
    }

    private StoreBasicInfo createStoreInfoDetail(Domain domain, User user, String subCategory) {
        boolean isFavorite = storeQueryService.isUserFavorite(domain, user);
        List<String> items = storeQueryService.getItems(domain);
        List<String> keywords = storeQueryService.getHashTags(domain);
        return storeMapper.toStoreBasicInfoDetail(domain, isFavorite, subCategory, items, keywords);
    }

    public StoreReviewResponseDto getStoreReview(Long categoryId, String email, Long storeId) {
        Category category = storeQueryService.getCategory(categoryId);
        User user = storeQueryService.getUserByEmail(email);
        Domain domain  = storeQueryService.getStore(category, storeId);
        ReviewInfoGroup reviewInfoGroup = reviewQueryService.getReviewInfoGroup(domain, user);

        return StoreReviewResponseDto.builder().
                categoryId(categoryId)
                .storeId(storeId)
                .result(storeMapper.createReviewInfo(reviewInfoGroup))
                .build();
    }


}
