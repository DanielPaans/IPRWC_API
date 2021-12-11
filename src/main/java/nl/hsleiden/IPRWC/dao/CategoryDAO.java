package nl.hsleiden.IPRWC.dao;

import nl.hsleiden.IPRWC.models.Category;
import nl.hsleiden.IPRWC.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryDAO {

    private final CategoryRepository CATEGORY_REPOSITORY;

    public CategoryDAO(CategoryRepository category_repository) {
        CATEGORY_REPOSITORY = category_repository;
    }

    public void storeCategory(Category category) {
        CATEGORY_REPOSITORY.save(category);
    }

    public Category findCategory(String categoryName) {
        return CATEGORY_REPOSITORY.findCategoryByName(categoryName);
    }
}
