package nl.hsleiden.IPRWC.models;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table
public class Product {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private int amount;
    @Column(nullable = false)
    private float price;
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "product_category",
            joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id", updatable = false, insertable = false)})
    private Set<Category> categories;
    private String imagePath;

    public Product() {}

    public Product(String name, String description, float price, Set<Category> categories) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.categories = categories;
    }

    public Product(String name, String description, float price, Set<Category> categories, int amount) {
        this(name, description, price, categories);
        this.amount = amount;
    }

    public Product(String name, String description, float price, Set<Category> categories, String imagePath) {
        this(name, description, price, categories);
        this.imagePath = imagePath;
    }

    public Product(String name, String description, float price, Set<Category> categories, int amount, String imagePath) {
        this(name, description, price, categories, amount);
        this.imagePath = imagePath;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public void addCategory(Category category) {
        this.categories.add(category);
    }

    public void removeCategory(Category category) {
        this.categories.remove(category);
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
