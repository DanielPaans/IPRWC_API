package nl.hsleiden.IPRWC.models;

import javax.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    public User() {}

    public User(String email) {
        this.email = email;
    }

    public User(String email, Role role) {
        this(email);
        this.role = role;
    }
}
