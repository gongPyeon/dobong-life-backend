package dobong.life.base.store.service.query;

import dobong.life.base.store.Category;
import dobong.life.base.store.Tag;
import dobong.life.base.store.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HashTagQueryService {
    private final TagRepository tagRepository;

    public List<Tag> getAllTag(){
        return tagRepository.findAll();
    }
}
