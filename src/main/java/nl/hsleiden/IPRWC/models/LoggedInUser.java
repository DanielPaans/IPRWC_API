package nl.hsleiden.IPRWC.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class LoggedInUser extends User {

    @Column(nullable = false)
    private String username;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JoinTable(name = "user_product",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")})
    private Set<Product> recentlySearched;

    public LoggedInUser() {}

    public LoggedInUser(String email, String username) {
        super(email);
        this.username = username;
    }

    public LoggedInUser(String email, String username, Set<Product> recentlySearched) {
        this(email, username);
        this.recentlySearched = recentlySearched;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Product> getRecentlySearched() {
        return recentlySearched;
    }

    public void setRecentlySearched(Set<Product> recentlySearched) {
        this.recentlySearched = recentlySearched;
    }
}
