package nl.hsleiden.IPRWC.httpResponses;

import java.util.Date;
import java.util.UUID;

public class AuthenticationResponse {

    private UUID id;
    private String role;
    private String jwt;
    private Date expireTime;

    public AuthenticationResponse(UUID id, String role, String jwt, Date expireTime) {
        this.id = id;
        this.role = role;
        this.jwt = jwt;
        this.expireTime = expireTime;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public Date getExpiresAt() {
        return expireTime;
    }
}
