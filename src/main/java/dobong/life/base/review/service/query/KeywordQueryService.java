package dobong.life.base.review.service.query;

import dobong.life.base.review.Keyword;
import dobong.life.base.review.exception.KeywordNotFoundException;
import dobong.life.base.review.repository.KeywordRepository;
import dobong.life.base.user.exception.UserNotFoundException;
import dobong.life.global.util.response.status.BaseErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KeywordQueryService {
    private final KeywordRepository keywordRepository;

    public List<Keyword> getKeywordAll(){
        return keywordRepository.findAll();
    }

    public Keyword getKeyword(String keyword) {
        return keywordRepository.findByName(keyword)
                .orElseThrow(() -> new KeywordNotFoundException(BaseErrorCode.NOT_FOUND,
                        "[ERROR] "+keyword+"를 찾을 수 없습니다"));
    }
}
