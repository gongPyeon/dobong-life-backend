package dobong.life.base.store.service.query;

import dobong.life.base.store.repository.CategoryRepository;
import dobong.life.base.store.Category;
import dobong.life.base.store.exception.CategoryNotFoundException;
import dobong.life.global.util.response.status.BaseErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryQueryService {
    private final CategoryRepository categoryRepository;

    public Category getCategory(Long categoryId){
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(BaseErrorCode.NOT_FOUND,"[ERROR] "+categoryId+"에 해당하는 카테고리를 찾을 수 없습니다"));
    }

    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }

    public List<String> getCategories(Category categoryByDomain) {
        List<String> categories = new ArrayList<>();
        String categoryName = categoryByDomain.getCategoryName();
        String subCategoryName = categoryByDomain.getSubCategoryName();
        String name = categoryByDomain.getName();

        categories.add(categoryName);
        categories.add(subCategoryName);
        categories.add(name);

        return categories;
    }
}
