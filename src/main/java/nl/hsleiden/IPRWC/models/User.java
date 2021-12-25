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

    private String email;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(unique = true, length = 60, nullable = false)
    private String password;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String email) {
        this(username, password);
        this.email = email;
    }

    public User(String username, String password, String email, Role role) {
        this(username, password, email);
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
}
