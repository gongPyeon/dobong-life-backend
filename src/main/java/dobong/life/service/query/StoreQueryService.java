package dobong.life.service.query;

import dobong.life.entity.Category;
import dobong.life.entity.Domain;
import dobong.life.entity.User;
import dobong.life.repository.CategoryRepository;
import dobong.life.repository.FavoriteRepository;
import dobong.life.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.NotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreQueryService {
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final FavoriteRepository favoriteRepository;

    public Category getCategory(Long categoryId){
        return categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException("카테고리를 찾을 수 없습니다."));
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("사용자를 찾을 수 없습니다."));
    }

    public boolean isUserFavorite(Domain domain, User user){
        return favoriteRepository.findByDomainAndUser(domain, user).isPresent();
    }
}
