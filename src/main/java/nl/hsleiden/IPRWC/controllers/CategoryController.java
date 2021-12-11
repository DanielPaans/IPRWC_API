package nl.hsleiden.IPRWC.controllers;

import nl.hsleiden.IPRWC.dao.CategoryDAO;
import nl.hsleiden.IPRWC.httpResponses.Response;
import nl.hsleiden.IPRWC.models.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${DEFAULT_PATH}${CATEGORY}")
public class CategoryController {

    private final CategoryDAO CATEGORY_DAO;

    public CategoryController(CategoryDAO categoryDAO) {
        CATEGORY_DAO = categoryDAO;
    }

    @PostMapping
    public ResponseEntity<Response> postCategory(@RequestBody Category category) {
        CATEGORY_DAO.storeCategory(category);
        return ResponseEntity.ok(new Response("category added"));
    }

    @GetMapping
    public Category getCategory(@RequestBody String categoryName) {
        return CATEGORY_DAO.findCategory(categoryName);
    }
}
