package nl.hsleiden.IPRWC.dao;

import nl.hsleiden.IPRWC.models.Product;
import nl.hsleiden.IPRWC.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.UUID;

@Service
public class ProductDAO {

    private final ProductRepository PRODUCT_REPOSITORY;

    public ProductDAO(ProductRepository productRepository) {
        PRODUCT_REPOSITORY = productRepository;
    }

    public List<Product> findProducts() {
        return PRODUCT_REPOSITORY.findAll();
    }

    public List<Product> findProductsByKeyword(String keyword) {
        return PRODUCT_REPOSITORY.findProductsByKeyword(keyword);
    }

    public List<Product> findProductsByCategory(UUID categoryId, String keyword) {
        return PRODUCT_REPOSITORY.findProductsByCategory(categoryId, keyword);
    }

    public List<Product> storeProduct(List<Product> product) {
        return PRODUCT_REPOSITORY.saveAll(product);
    }

    public void deleteProduct(UUID productId) {
        PRODUCT_REPOSITORY.deleteById(productId);
    }

    public void changeName(UUID id, String name) {
        PRODUCT_REPOSITORY.updateProductName(id, name);
    }

    public void changeDescription(UUID id, String description) {
        PRODUCT_REPOSITORY.updateProductDescription(id, description);
    }

    public void changeAmount(UUID id, int amount) {
        PRODUCT_REPOSITORY.updateProductAmount(id, amount);
    }

    public void changePrice(UUID id, float price) {
        PRODUCT_REPOSITORY.updateProductPrice(id, price);
    }

    public void changeImage(UUID id, String imagePath) {
        PRODUCT_REPOSITORY.updateProductImage(id, imagePath);
    }

    public void addCategory(UUID id, UUID categoryId) {
        PRODUCT_REPOSITORY.addCategory(id, categoryId);
    }

    public void removeCategory(UUID id, UUID categoryId) {
        PRODUCT_REPOSITORY.removeCategory(id, categoryId);
    }
}
