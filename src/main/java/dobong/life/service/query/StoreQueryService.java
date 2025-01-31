package dobong.life.service.query;

import dobong.life.dto.info.StoreGroup;
import dobong.life.entity.Category;
import dobong.life.entity.Domain;
import dobong.life.entity.User;
import dobong.life.repository.*;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreQueryService {
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final FavoriteRepository favoriteRepository;
    private final DomainRepository domainRepository;
    private final SubTagRepository subTagRepository;

    public Category getCategory(Long categoryId){
        return categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException("카테고리를 찾을 수 없습니다."));
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("사용자를 찾을 수 없습니다."));
    }

    public boolean isUserFavorite(Domain domain, User user){
        return favoriteRepository.findByDomainAndUser(domain, user).isPresent();
    }

    public Domain getStore(Category category, Long storeId) {
        return domainRepository.findById(storeId).orElseThrow(() -> new NotFoundException("가게를 찾을 수 없습니다."));
    }

    public List<String> getItems(Domain domain) {
        List<String> itemList = Arrays.asList(domain.getItemName().split(","));
        return itemList;
    }

    public List<String> getHashTags(Domain domain) {
        //return domainRepository.findByTag(domain.getTag()).stream()
        //domain.getSubTag()
        return null;
    }
}
