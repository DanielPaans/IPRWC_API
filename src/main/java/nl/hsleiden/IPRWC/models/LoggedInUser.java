package nl.hsleiden.IPRWC.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class LoggedInUser extends User {

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JoinTable(name = "user_product",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")})
    private Set<Product> recentlySearched;

    public LoggedInUser() {}

    public LoggedInUser(String email, String username, String password) {
        super(email, username, password);
    }

    public LoggedInUser(String email, String username, String password, Set<Product> recentlySearched) {
        this(email, username, password);
        this.recentlySearched = recentlySearched;
    }

    public Set<Product> getRecentlySearched() {
        return recentlySearched;
    }

    public void setRecentlySearched(Set<Product> recentlySearched) {
        this.recentlySearched = recentlySearched;
    }
}
