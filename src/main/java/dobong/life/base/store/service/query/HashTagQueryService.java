package dobong.life.base.store.service.query;

import dobong.life.base.store.Tag;
import dobong.life.base.store.exception.HashTagNotFoundException;
import dobong.life.base.store.repository.TagRepository;
import dobong.life.global.util.response.status.BaseErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HashTagQueryService {
    private final TagRepository tagRepository;

    public List<Tag> getAllTag(String categoryName){
        return tagRepository.findAll(categoryName)
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new HashTagNotFoundException(BaseErrorCode.HASHTAG_NOT_FOUND,
                        "[ERROR] "+categoryName+"에 해당되는 태그가 없습니다"));
    }
}
