package dobong.life.service.query;

import dobong.life.dto.info.TagGroup;
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

    public List<TagGroup> getTagGroups(){
        return tagRepository.findAll().stream()
                .map(this::createTagGroup)
                .collect(Collectors.toList());
    }

    private TagGroup createTagGroup(Tag tag) {
        List<Domain> domains = domainTagRepository.findByTag(tag).stream()
                .map(DomainTag::getDomain)
                .collect(Collectors.toList());

        return new TagGroup(tag, domains);
    }

}
