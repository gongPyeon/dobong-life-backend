package dobong.life.service.query;

import dobong.life.entity.*;
import dobong.life.repository.*;
import dobong.life.util.exception.DomainNotFoundException;
import dobong.life.util.exception.DuplicateException;
import dobong.life.util.exception.MessageException;
import dobong.life.util.exception.SubCategoryNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

import static dobong.life.util.response.status.BaseErrorCode.DB_NOT_SAVE;

@Service
@RequiredArgsConstructor
public class StoreQueryService {
    private final DomainLikeRepository domainLikeRepository;
    private final DomainRepository domainRepository;
    private final MiddleCategoryRepository middleCategoryRepository;
    private final ReviewLikeRepository reviewLikeRepository;
    public Domain getDomain(Long storeId) {
        return domainRepository.findById(storeId)
                .orElseThrow(() -> new DomainNotFoundException(storeId));
    }

    public List<String> getMenus(Domain domain) {
        List<String> itemList = Arrays.asList(domain.getItemName().split(","));
        return itemList;
    }

    public List<String> getHashTags(Domain domain) {
        return middleCategoryRepository.findByDomain(domain).stream()
                .filter(Objects::nonNull)
                .map(d -> d.getSubTag().getSubTagName())
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public String getSubCategory(Domain domain) {
        return middleCategoryRepository.findByDomain(domain).stream()
                .findFirst()
                .map(middleCategory -> middleCategory.getSubCategory().getSubCategoryName())
                .orElseThrow(() -> new SubCategoryNotFoundException(domain.getName()));  // 없으면 예외 던짐
    }

    @Transactional
    public void updateStoreLike(User user, Domain domain) {
        if(checkDomainLike(user, domain))
            throw new DuplicateException(domain.getItemName());

        DomainLike domainLike = new DomainLike(user, domain);
        domainLikeRepository.save(domainLike);
    }

    private boolean checkDomainLike(User user, Domain domain) {
        return domainLikeRepository.findByDomainAndUser(domain, user).isPresent();
    }

    @Transactional
    public void updateReviewLike(User user, Review review) {
        if(checkReviewLike(user, review))
            throw new DuplicateException(review.getDomain().getName());

        ReviewLike reviewLike = new ReviewLike(user, review);
        reviewLikeRepository.save(reviewLike);
    }

    private boolean checkReviewLike(User user, Review review) {
        return reviewLikeRepository.findByUserAndReview(user, review).isPresent();
    }
}
