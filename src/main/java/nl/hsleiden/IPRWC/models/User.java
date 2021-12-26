package nl.hsleiden.IPRWC.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table
public class User {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(nullable = false)
    private String email;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(unique = true, length = 60, nullable = false)
    private String password;
    @Column(length = 6, nullable = false)
    private String postalCode;
    @Column(nullable = false)
    private String city;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    public User() {}

    public User(String username, String password, String email, String postalCode, String city) {
        this.username = username;
        this.password = password;
        this.postalCode = postalCode;
        this.email = email;
        this.city = city;
    }

    public User(String username, String password, String email, String postalCode, String city, Role role) {
        this(username, password, email, postalCode, city);
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
