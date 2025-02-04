package dobong.life.service.query;

import dobong.life.dto.info.StoreGroup;
import dobong.life.entity.Category;
import dobong.life.entity.Domain;
import dobong.life.entity.User;
import dobong.life.repository.*;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreQueryService {
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final DomainLikeRepository domainLikeRepository;
    private final DomainRepository domainRepository;
    private final MiddleCategoryRepository middleCategoryRepository;

    public Category getCategory(Long categoryId){

        return categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException("카테고리를 찾을 수 없습니다."));
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("사용자를 찾을 수 없습니다."));
    }

    public boolean isUserFavorite(Domain domain, User user){
        return domainLikeRepository.findByDomainAndUser(domain, user).isPresent();
    }

    public Domain getStore(Long storeId) {
        return domainRepository.findById(storeId).orElseThrow(() -> new NotFoundException("가게를 찾을 수 없습니다."));
    }

    public List<String> getItems(Domain domain) {
        List<String> itemList = Arrays.asList(domain.getItemName().split(","));
        return itemList;
    }

    public List<String> getHashTags(Domain domain) {
        return middleCategoryRepository.findByDomain(domain).stream()
                .map(d -> d.getSubTag().getSubTagName())
                .collect(Collectors.toList());
    }

    public String getSubCategory(Domain domain) {
        return middleCategoryRepository.findByDomain(domain).get(0).getSubCategory().getSubCategoryName();
        // TODO: get(0)이 아니라 다른 방식 ?
    }

    public List<Domain> getStoreLike(Category category, User user) {
        List<Domain> domains = domainLikeRepository.findByUser(user).stream()
                .map(d -> d.getDomain())
                .filter(domain -> domain.getCategory() == category)
                .collect(Collectors.toList());

        if (domains.isEmpty()) {
            throw new NotFoundException("가게를 찾을 수 없습니다."); // 다른 예외처리
        }

        return domains;
    }

}
