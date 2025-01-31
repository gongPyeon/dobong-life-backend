package dobong.life.service.query;

import dobong.life.dto.info.SubTagDomain;
import dobong.life.dto.info.TagGroup;
import dobong.life.entity.*;
import dobong.life.repository.DomainRepository;
import dobong.life.repository.SubCategoryRepository;
import dobong.life.repository.SubTagRepository;
import dobong.life.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TagQueryService {
    private final TagRepository tagRepository;
    private final DomainRepository domainRepository;
    private final SubTagRepository subTagRepository;
    private final SubCategoryRepository subCategoryRepository;

    private static final int MAX_DOMAINS_PER_SUBTAG = 4;

    public List<TagGroup> getTagGroups(Category category) {
        return tagRepository.findByCategory(category).stream()
                .map(this::createTagGroupByTag)
                .collect(Collectors.toList());
    }

    private TagGroup createTagGroupByTag(Tag tag) {
        List<SubTagDomain> subTagDomains = subTagRepository.findByTag(tag).stream()
                .map(this::createSubTagDomain)
                .collect(Collectors.toList());

        return new TagGroup(tag, subTagDomains);
    }

    private SubTagDomain createSubTagDomain(SubTag subTag) {
        List<Domain> domains = domainRepository.findBySubTag(subTag).stream()
                .limit(MAX_DOMAINS_PER_SUBTAG)
                .collect(Collectors.toList());
        return new SubTagDomain(subTag, domains);
    }

    public List<TagGroup> getTagGroupsByQuery(Category category, String query) {
        return tagRepository.findByCategory(category).stream()
                .map(tag -> createTagGroupByTagAndQuery(tag, query))
                .collect(Collectors.toList());
    }

    private TagGroup createTagGroupByTagAndQuery(Tag tag, String query) {
        List<SubTagDomain> subTagDomains = subTagRepository.findByTag(tag).stream()
                .map(subTag -> createSubTagDomainByQuery(subTag, query))
                .collect(Collectors.toList());

        return new TagGroup(tag, subTagDomains);
    }

    private SubTagDomain createSubTagDomainByQuery(SubTag subTag, String query) {
        List<Domain> domains = domainRepository.findBySubTagAndQuery(subTag, query).stream()
                .collect(Collectors.toList());
        return new SubTagDomain(subTag, domains);
    }

    public List<Domain> getTagGroupsFilter(List<String> categoryName, List<String> subTagName) {
        List<Domain> domains = domainRepository.findByFilters(categoryName, subTagName).stream()
                .collect(Collectors.toList());

        return domains;
    }

    public List<TagGroup> getTagGroupsMore(Category category, Long tagId, String subTagName) {
        return subTagRepository.findBySubTagName(subTagName).stream()
                .map(this::createTagGroupBySubTag)
                .collect(Collectors.toList());
    }

    private TagGroup createTagGroupBySubTag(SubTag subTag) {
        List<Domain> domains = domainRepository.findBySubTag(subTag).stream()
                .limit(MAX_DOMAINS_PER_SUBTAG)
                .collect(Collectors.toList());

        SubTagDomain subTagDomain = new SubTagDomain(subTag, domains);

        return new TagGroup(subTag.getTag(), Collections.singletonList(subTagDomain));
    }
}
