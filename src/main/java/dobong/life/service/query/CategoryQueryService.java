package dobong.life.service.query;

import dobong.life.entity.Category;
import dobong.life.repository.CategoryRepository;
import dobong.life.util.exception.CategoryNotFoundException;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryQueryService {
    private final CategoryRepository categoryRepository;

    public Category getCategory(Long categoryId){
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
    }
}
