package dobong.life.service.query;

import dobong.life.dto.info.SubTagDomain;
import dobong.life.dto.info.TagGroup;
import dobong.life.entity.*;
import dobong.life.repository.DomainRepository;
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

    private static final int MAX_DOMAINS_PER_SUBTAG = 4;
    private static final int MAX_DOMAINS_MORE = 25;

    public List<TagGroup> getTagGroups(Category category) {
        return tagRepository.findByCategory(category).stream()
                .map(this::createTagGroupByTag)
                .collect(Collectors.toList());
    }

    private TagGroup createTagGroupByTag(Tag tag) {
        List<SubTagDomain> subTagDomains = subTagRepository.findByTag(tag).stream()
                .map(sub -> createSubTagDomain(sub, MAX_DOMAINS_PER_SUBTAG))
                .collect(Collectors.toList());

        return new TagGroup(tag, subTagDomains);
    }

    private SubTagDomain createSubTagDomain(SubTag subTag, int max) {
        List<Domain> domains = domainRepository.findBySubTag(subTag).stream()
                .limit(max)
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
        return tagRepository.findByCategoryAndId(category, tagId).stream()
                .map(tag -> createTagGroupMoreByTag(tag, subTagName))
                .collect(Collectors.toList());
    }

    private TagGroup createTagGroupMoreByTag(Tag tag, String subTagName) {
        List<SubTagDomain> subTagDomains = subTagRepository.findBySubTagName(subTagName).stream()
                .map(sub -> createSubTagDomain(sub, MAX_DOMAINS_MORE))
                .collect(Collectors.toList());

        return new TagGroup(tag, subTagDomains);
    }
}
