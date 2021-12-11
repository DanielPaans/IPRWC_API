package nl.hsleiden.IPRWC.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class LoggedInUser extends User {

    @Column(nullable = false)
    private String username;
    @Column(unique = true, length = 60, nullable = false)
    private String password;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JoinTable(name = "user_product",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")})
    private Set<Product> recentlySearched;

    public LoggedInUser() {}

    public LoggedInUser(String email, String username, String password) {
        super(email);
        this.username = username;
        this.password = password;
    }

    public LoggedInUser(String email, String username, String password, Set<Product> recentlySearched) {
        this(email, username, password);
        this.recentlySearched = recentlySearched;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Product> getRecentlySearched() {
        return recentlySearched;
    }

    public void setRecentlySearched(Set<Product> recentlySearched) {
        this.recentlySearched = recentlySearched;
    }
}
