package dobong.life.service.query;

import dobong.life.entity.*;
import dobong.life.repository.*;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreQueryService {
    private final DomainLikeRepository domainLikeRepository;
    private final DomainRepository domainRepository;
    private final MiddleCategoryRepository middleCategoryRepository;
    private final ReviewLikeRepository reviewLikeRepository;

    public boolean isUserFavorite(Domain domain, User user){
        return domainLikeRepository.findByDomainAndUser(domain, user).isPresent();
    }

    public Domain getStore(Long storeId) {
        return domainRepository.findById(storeId).orElseThrow(() -> new NotFoundException("가게를 찾을 수 없습니다."));
    }

    public List<String> getMenus(Domain domain) {
        List<String> itemList = Arrays.asList(domain.getItemName().split(","));
        return itemList;
    }

    public List<String> getHashTags(Domain domain) {
        return middleCategoryRepository.findByDomain(domain).stream()
                .map(d -> d.getSubTag().getSubTagName())
                .collect(Collectors.toList());
    }

    public String getSubCategory(Domain domain) {
        return middleCategoryRepository.findByDomain(domain).stream()
                .findFirst()
                .map(middleCategory -> middleCategory.getSubCategory().getSubCategoryName())
                .orElseThrow(() -> new NoSuchElementException("No subcategory found for the given domain"));  // 없으면 예외 던짐
    }

    public void updateStoreLike(User user, Domain domain) {
        DomainLike domainLike = new DomainLike(user, domain);
        domainLikeRepository.save(domainLike);
    }

    public void updateReviewLike(User user, Review review) {
        ReviewLike reviewLike = new ReviewLike(user, review);
        reviewLikeRepository.save(reviewLike);
    }
}
