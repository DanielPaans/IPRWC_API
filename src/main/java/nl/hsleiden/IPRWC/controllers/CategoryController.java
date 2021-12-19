package nl.hsleiden.IPRWC.controllers;

import nl.hsleiden.IPRWC.dao.CategoryDAO;
import nl.hsleiden.IPRWC.httpResponses.Response;
import nl.hsleiden.IPRWC.models.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("${DEFAULT_PATH}${CATEGORY}")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {

    private final CategoryDAO CATEGORY_DAO;

    public CategoryController(CategoryDAO categoryDAO) {
        CATEGORY_DAO = categoryDAO;
    }

    @PostMapping
    public Category postCategory(@RequestBody Category category) {
        return CATEGORY_DAO.storeCategory(category);
    }

    @GetMapping
    public List<Category> getCategory(@RequestParam Optional<String> categoryName) {
        if(categoryName.isPresent()) {
            return CATEGORY_DAO.findCategory(categoryName.get());
        } else {
            return CATEGORY_DAO.getGategories();
        }
    }
}
