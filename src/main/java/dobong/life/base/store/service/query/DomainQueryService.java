package dobong.life.base.store.service.query;

import dobong.life.base.review.Review;
import dobong.life.base.review.ReviewLike;
import dobong.life.base.review.exception.ReviewNotFoundException;
import dobong.life.base.store.Domain;
import dobong.life.base.store.DomainLike;
import dobong.life.base.store.exception.DomainNotFoundException;
import dobong.life.base.store.repository.DomainLikeRepository;
import dobong.life.base.store.repository.DomainRepository;
import dobong.life.base.user.User;
import dobong.life.global.util.response.status.BaseErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DomainQueryService {
    private final DomainRepository domainRepository;
    private final DomainLikeRepository domainLikeRepository;
    private static final int MAX = 3;

    public List<Domain> findByCategoryName(String categoryName, int max, int page){
        int offset = max * page;
        return domainRepository.findByCategoryName(categoryName)
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new DomainNotFoundException
                        (BaseErrorCode.NOT_FOUND, "[ERROR] " + categoryName + "에 해당하는 상점을 찾을 수 없습니다"))
                .stream()
                .skip(offset)
                .limit(max)
                .collect(Collectors.toList());
    }

    public int getPageSize(String categoryName){
        return domainRepository.findByCategoryName(categoryName)
                .orElseThrow(() -> new DomainNotFoundException
                        (BaseErrorCode.NOT_FOUND, "[ERROR] " + categoryName + "에 해당하는 상점을 찾을 수 없습니다")).size();
    }

    public List<Domain> findByHashTag(String hashTag) {
        return domainRepository.findByHashTag(hashTag)
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new DomainNotFoundException
                        (BaseErrorCode.NOT_FOUND, "[ERROR] " + hashTag + "에 해당하는 상점을 찾을 수 없습니다"))
                .stream()
                .limit(MAX)
                .collect(Collectors.toList());
    }

    public boolean getLikeByUser(Domain domain, Long userId) {
        return domainLikeRepository.findByDomainAndUser(domain, userId).isPresent();
    }

    public List<Domain> findByQueryAndFilter(String query, List<String> filter) {
        return domainRepository.findByQueryAndFilter(query, filter)
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new DomainNotFoundException(BaseErrorCode.NOT_FOUND, "[ERROR] 검색한 상점을 찾을 수 없습니다"))
                .stream()
                .collect(Collectors.toList());
    }

    public Domain findById(Long storeId) {
        return domainRepository.findById(storeId)
                .orElseThrow(() -> new DomainNotFoundException(BaseErrorCode.NOT_FOUND, "[ERROR] 상점 아이디 " + storeId + "를 찾을 수 없습니다"));
    }
    public List<Domain> findByUserId(Long userId) {
        return domainLikeRepository.findByUserId(userId)
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new DomainNotFoundException(BaseErrorCode.NOT_FOUND, "[ERROR] 사용자가 찜한 목록이 없습니다"));
    }

    @Transactional
    public void updateStoreLike(User user, Domain domain) {
        if(checkDomainLike(user.getId(), domain)) {
            removeStoreLike(user, domain);
            return;
        }

        DomainLike domainLike = new DomainLike(user, domain);
        domainLikeRepository.save(domainLike);
    }

    private boolean checkDomainLike(Long usrId, Domain domain) {
        return domainLikeRepository.findByDomainAndUser(domain, usrId).isPresent();
    }

    private DomainLike getStoreLike(User user, Domain domain) {
        return domainLikeRepository.findByDomainAndUser(domain, user.getId())
                .orElseThrow(() -> new ReviewNotFoundException(BaseErrorCode.NOT_FOUND,
                        "[ERROR] "+domain.getId()+"에 해당하는 상점을 찾을 수 없습니다"));
    }

    @Transactional
    public void removeStoreLike(User user, Domain domain){
        domainLikeRepository.delete(getStoreLike(user, domain));
    }

}
