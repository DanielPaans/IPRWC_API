package nl.hsleiden.IPRWC.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Admin extends User {

    @Column(unique = true, nullable = false)
    private String username;
    @Column(unique = true, length = 60, nullable = false)
    private String password;

    public Admin() {}

    public Admin(String email, String username, String password) {
        super(email);
        this.username = username;
        this.password = password;
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
