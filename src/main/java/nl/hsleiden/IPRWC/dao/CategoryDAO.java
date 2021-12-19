package nl.hsleiden.IPRWC.dao;

import nl.hsleiden.IPRWC.models.Category;
import nl.hsleiden.IPRWC.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryDAO {

    private final CategoryRepository CATEGORY_REPOSITORY;

    public CategoryDAO(CategoryRepository categoryRepository) {
        CATEGORY_REPOSITORY = categoryRepository;
    }

    public Category storeCategory(Category category) {
        return CATEGORY_REPOSITORY.save(category);
    }

    public List<Category> findCategory(String categoryName) {
        return CATEGORY_REPOSITORY.findCategoryByName(categoryName);
    }

    public List<Category> getGategories() {
        return CATEGORY_REPOSITORY.findAll();
    }
}
