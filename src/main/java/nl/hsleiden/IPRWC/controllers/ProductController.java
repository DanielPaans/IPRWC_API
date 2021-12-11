package nl.hsleiden.IPRWC.controllers;

import nl.hsleiden.IPRWC.dao.ProductDAO;
import nl.hsleiden.IPRWC.httpResponses.Response;
import nl.hsleiden.IPRWC.models.Category;
import nl.hsleiden.IPRWC.models.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("${DEFAULT_PATH}${PRODUCT}")
public class ProductController {

    private final ProductDAO PRODUCT_DAO;
    private final CategoryController CATEGORY_CONTROLLER;

    public ProductController(ProductDAO productDAO, CategoryController categoryController) {
        PRODUCT_DAO = productDAO;
        CATEGORY_CONTROLLER = categoryController;
    }

    @GetMapping
    public List<Product> getProducts(@RequestParam(value = "category") Optional<String> category) {
        if(category.isPresent()) {
            UUID categoryId = CATEGORY_CONTROLLER.getCategory(category.get()).getId();
            return PRODUCT_DAO.findProductsByCategory(categoryId);
        } else {
            return PRODUCT_DAO.findProducts();
        }
    }

    @PostMapping
    public ResponseEntity<Response> postProduct(@RequestBody List<Product> product) {
        PRODUCT_DAO.storeProduct(product);
        return ResponseEntity.ok(new Response("Product stored"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteProduct(@PathVariable UUID id) {
        PRODUCT_DAO.deleteProduct(id);
        return ResponseEntity.ok(new Response("Product deleted"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> editProduct(@PathVariable UUID id,
                                                @RequestParam("name") Optional<String> name,
                                                @RequestParam("description") Optional<String> description,
                                                @RequestParam("amount") Optional<Integer> amount,
                                                @RequestParam("price") Optional<Float> price,
                                                @RequestParam("image") Optional<String> imagePath,
                                                @RequestParam("addCategory") Optional<String> addCategory,
                                                @RequestParam("removeCategory") Optional<String> removeCategory) {
        name.ifPresent(s -> PRODUCT_DAO.changeName(id, s));
        description.ifPresent(s -> PRODUCT_DAO.changeDescription(id, s));
        amount.ifPresent(s -> PRODUCT_DAO.changeAmount(id, s));
        price.ifPresent(s -> PRODUCT_DAO.changePrice(id, s));
        imagePath.ifPresent(s -> PRODUCT_DAO.changeImage(id, s));
        addCategory.ifPresent(s -> PRODUCT_DAO.addCategory(id, CATEGORY_CONTROLLER.getCategory(s).getId()));
        removeCategory.ifPresent(s -> PRODUCT_DAO.removeCategory(id, CATEGORY_CONTROLLER.getCategory(s).getId()));

        return ResponseEntity.ok(new Response("product updated"));
    }
}
