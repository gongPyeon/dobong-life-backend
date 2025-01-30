package dobong.life.service.query;

import dobong.life.dto.info.SubTagDomain;
import dobong.life.dto.info.TagGroup;
import dobong.life.entity.*;
import dobong.life.repository.DomainRepository;
import dobong.life.repository.SubCategoryRepository;
import dobong.life.repository.SubTagRepository;
import dobong.life.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
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
//        return subCategoryRepository.findByCategoryAndQuery(category, query).stream()
//                .map(this::createTagGroupBySubCategory)
//                .collect(Collectors.toList());

        return subCategoryRepository.findByCategory(category).stream()
                .map(this::createTagGroupBySubCategory)
                .collect(Collectors.toList());
    }

    private TagGroup createTagGroupBySubCategory(SubCategory subCategory) {
        Domain domain = domainRepository.findBySubCategory(subCategory);
        SubTag subTag = domain.getSubTag();

        SubTagDomain subTagDomain = new SubTagDomain(
                subTag,
                Collections.singletonList(domain)
        );

        return new TagGroup(subTag.getTag(), Collections.singletonList(subTagDomain));
    }

    public List<TagGroup> getTagGroupsMore(Category category, Long tagCategoryId, String hashTag) {
        return subTagRepository.findBySubTagName(hashTag).stream()
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
