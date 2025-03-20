package dobong.life.base.review.service.query;

import dobong.life.base.review.Middle;
import dobong.life.base.review.Review;
import dobong.life.base.review.exception.ReviewNotFoundException;
import dobong.life.base.review.repository.MiddleRepository;
import dobong.life.base.store.Domain;
import dobong.life.base.user.User;
import dobong.life.global.util.response.status.BaseErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MiddleQueryService {
    private final MiddleRepository middleRepository;
    @Transactional
    public void saveMiddle(List<Middle> middles) {
        middleRepository.saveAll(middles);
    }

    public List<String> sortKeywords(Domain domain) {
        List<String> keywordList = middleRepository.findByDomain(domain);

        Map<String, Long> frequencyMap = keywordList.stream()
                .collect(Collectors.groupingBy(keyword -> keyword, Collectors.counting()));

        List<String> sortedKeywords = getStrings(frequencyMap);

        return sortedKeywords;

    }

    public List<String> getKeywords(Review review) {
        return middleRepository.findByReview(review)
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new ReviewNotFoundException
                (BaseErrorCode.MIDDLE_NOT_FOUND, "[ERROR] 중간테이블에서 키워드를 찾을 수 없습니다"))
                .stream()
                .map(middle -> middle.getKeyword().getReviewKwdName())
                .collect(Collectors.toList());
    }

    private static List<String> getStrings(Map<String, Long> frequencyMap) {
        List<String> sortedKeywords = frequencyMap.entrySet().stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))  // 빈도수 내림차순 정렬
                .map(Map.Entry::getKey)  // 키워드만 추출
                .collect(Collectors.toList());
        return sortedKeywords;
    }
}
