package dobong.life.service.query;

import dobong.life.dto.info.ItemInfo;
import dobong.life.dto.info.StoreBasicInfo;
import dobong.life.dto.info.StoreDetailInfo;
import dobong.life.entity.*;
import dobong.life.repository.DomainLikeRepository;
import dobong.life.repository.DomainRepository;
import dobong.life.repository.SubTagRepository;
import dobong.life.repository.TagRepository;
import dobong.life.service.StoreMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Store;
import org.openqa.selenium.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TagQueryService {
    private final DomainLikeRepository domainLikeRepository;
    private final TagRepository tagRepository;
    private final DomainRepository domainRepository;
    private final SubTagRepository subTagRepository;
    private final StoreMapper storeMapper;

    private static final int MAX_DOMAINS_PER_SUBTAG = 4;
    private static final int MAX_DOMAINS_MORE = 25;

    public List<ItemInfo> getItemInfos(Category category, User user) {
        return tagRepository.findByCategory(category).stream()
                .map(tag -> getStoreInfosByTag(tag, user))
                .flatMap(List::stream)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }


    private List<ItemInfo> getStoreInfosByTag(Tag tag, User user) {  // ItemInfo -> List<ItemInfo>
        return subTagRepository.findByTag(tag).stream()
                .map(subTag -> getStoreInfoWithLimit(tag, subTag, user, MAX_DOMAINS_PER_SUBTAG))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());  // collect 추가
    }

    private ItemInfo getStoreInfoWithLimit(Tag tag, SubTag subTag, User user, int max) {
        List<StoreBasicInfo> stores = domainRepository.findBySubTag(subTag).stream()
                .map(domain -> mapToStoreInfo(domain, user))
                .limit(max)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return storeMapper.buildItemInfo(tag, subTag, stores);
    }

    public StoreBasicInfo mapToStoreInfo(Domain domain, User user) {
        boolean isLiked = domainLikeRepository.findByDomainAndUser(domain, user).isPresent();
        return storeMapper.buildStoreBasicInfo(domain, isLiked);
    }

    // search
    public List<ItemInfo> getItemInfosByQuery(Category category, User user, String query) {
        return tagRepository.findByCategory(category).stream()
                .map(tag -> getStoreInfosByTagByQuery(tag, user, query))
                .flatMap(List::stream)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }


    private List<ItemInfo> getStoreInfosByTagByQuery(Tag tag, User user, String query) {
        return subTagRepository.findByTag(tag).stream()
                .map(subTag -> getStoreInfoWithLimitByQuery(tag, subTag, user, query, MAX_DOMAINS_PER_SUBTAG))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());  // collect 추가
    }

    private ItemInfo getStoreInfoWithLimitByQuery(Tag tag, SubTag subTag, User user, String query, int max) {
        List<StoreBasicInfo> stores = domainRepository.findBySubTagAndQuery(subTag, query).stream()
                .map(domain -> mapToStoreInfo(domain, user))
                .limit(max)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return storeMapper.buildItemInfo(tag, subTag, stores);
    }

    // filter
    public List<StoreBasicInfo> mapToStoreInfosByFilter(User user, List<String> categoryNames, List<Long> subTagIds) {
        return domainRepository.findByFilters(categoryNames, subTagIds).stream()
                .map(domain -> mapToStoreInfo(domain, user))
                .collect(Collectors.toList());
    }

    // more
    public List<ItemInfo> getItemInfosMore(Category category, User user, Long tagId, Long subTagId) {
        return tagRepository.findByCategoryAndId(category, tagId).stream()
                .map(tag -> getStoreInfosMoreByTag(tag, user, subTagId))
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private List<ItemInfo> getStoreInfosMoreByTag(Tag tag, User user, Long subTagId) {
        return subTagRepository.findById(subTagId).stream()
                .map(sub -> getStoreInfoWithLimit(tag, sub, user, MAX_DOMAINS_MORE))
                .collect(Collectors.toList());
    }

    public List<String> getSubTagNames(List<Long> subTagIds) {
        return subTagIds.stream()
                .map(subTagId -> subTagRepository.findById(subTagId)
                        .map(subTag -> subTag.getSubTagName())
                        .orElseThrow(() -> new NoSuchElementException("SubTag not found for id: " + subTagId)))
                .collect(Collectors.toList());
    }

    // item
    public StoreDetailInfo mapToStoreDetailInfo(String subTagName, String location, List<String> menus, List<String> keywords) {
        return storeMapper.buildStoreDetailInfo(subTagName, location, menus, keywords);
    }

    // like
    public List<StoreBasicInfo> getStoreInfoWithLimitLike(Category category, User user) {
        List<StoreBasicInfo> stores = domainLikeRepository.findByUserJoinCategory(user, category).stream()
                .map(domainLike -> mapToStoreInfo(domainLike.getDomain(), user))
                .collect(Collectors.toList());

        return stores;
    }

}
