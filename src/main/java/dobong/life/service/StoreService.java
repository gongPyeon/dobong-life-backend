package dobong.life.service;

import dobong.life.dto.StoreItemResponseDto;
import dobong.life.dto.StoreListResponseDto;
import dobong.life.dto.info.StoreBasicInfo;
import dobong.life.dto.info.StoreGroup;
import dobong.life.dto.info.SubTagDomain;
import dobong.life.dto.info.TagGroup;
import dobong.life.entity.*;
import dobong.life.service.mapper.StoreMapper;
import dobong.life.service.query.StoreQueryService;
import dobong.life.service.query.TagQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreQueryService storeQueryService;
    private final TagQueryService tagQueryService;
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
                .map(tagGroup -> createStoreGroup(tagGroup, user))
                .filter(group -> !group.getStores().isEmpty())
                .collect(Collectors.toList());
    }

    private StoreGroup createStoreGroup(TagGroup tagGroup, User user) {
        return StoreGroup.builder()
                .tag(storeMapper.toTagInfo(tagGroup.getTag(), tagGroup.getSubTagDomains().getFirst().getSubTag())) // subTag?
                .stores(createStoreList(tagGroup.getSubTagDomains(), user))
                .build();
    }

    private List<StoreBasicInfo> createStoreList(List<SubTagDomain> subTagDomains, User user) {
        return subTagDomains.stream()
                .flatMap(subTagDomain -> subTagDomain.getDomains().stream())
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

    public StoreListResponseDto getStoreList(Long categoryId, String email, Long tagCategoryId, String hashTag) {
        Category category = storeQueryService.getCategory(categoryId);
        User user = storeQueryService.getUserByEmail(email);
        List<TagGroup> tagGroups = tagQueryService.getTagGroupsMore(category, tagCategoryId, hashTag);

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
}
