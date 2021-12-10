package nl.hsleiden.IPRWC.models;

import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table
public class Category {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    @Column(nullable = false, unique = true)
    private String name;

    public Category() {}

    public Category(String name) {
        this.name = name;
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
}
