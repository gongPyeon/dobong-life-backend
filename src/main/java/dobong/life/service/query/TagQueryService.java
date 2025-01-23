package dobong.life.service.query;

import dobong.life.dto.info.TagGroup;
import dobong.life.entity.Category;
import dobong.life.entity.Domain;
import dobong.life.entity.DomainTag;
import dobong.life.entity.Tag;
import dobong.life.repository.DomainTagRepository;
import dobong.life.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagQueryService {

    private final TagRepository tagRepository;
    private final DomainTagRepository domainTagRepository;
    private static final int SIZE = 4; // 가져올 domain 개수

    public List<TagGroup> getTagGroups(Category category){
        return tagRepository.findByCategory(category).stream()
                .map(this::createTagGroup)
                .collect(Collectors.toList());
    }

    private TagGroup createTagGroup(Tag tag) {
        List<Domain> domains = domainTagRepository.findByTag(tag).stream()
                .map(DomainTag::getDomain)
                .limit(SIZE) // query 최적화를 위해서는 데이터베이스에서 가져올때 4개로 설정하는것이 좋다 - 고민해보기
                .collect(Collectors.toList());

        return new TagGroup(tag, domains);
    }

    public List<TagGroup> getTagGroupsByQuery(Category category, String query) {
        return tagRepository.findByCategoryAndQuery(category, query).stream()
                .map(this::createTagGroup)
                .collect(Collectors.toList());
    }

    public List<TagGroup> getTagGroupsMore(Category category, Long tagCategoryId, String hashTag) {
        return tagRepository.findByIdAndCategoryAndSubTagName(tagCategoryId, category, hashTag).stream()
                .map(this::createTagGroup)
                .collect(Collectors.toList());
    }
}
