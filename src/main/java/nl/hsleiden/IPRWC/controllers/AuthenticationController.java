package nl.hsleiden.IPRWC.controllers;

import nl.hsleiden.IPRWC.JwtUtil;
import nl.hsleiden.IPRWC.dao.UserDetailsDAO;
import nl.hsleiden.IPRWC.httpResponses.AuthenticationRequest;
import nl.hsleiden.IPRWC.httpResponses.AuthenticationResponse;
import nl.hsleiden.IPRWC.models.LoggedInUser;
import nl.hsleiden.IPRWC.models.Role;
import nl.hsleiden.IPRWC.models.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${DEFAULT_PATH}${AUTHENTICATE}")
@CrossOrigin(origins = {"https://www.rockingman.nl", "https://rockingman.nl"})
public class AuthenticationController {

    private final AuthenticationManager AUTHENTICATION_MANAGER;
    private final UserDetailsDAO USER_DETAILS_DAO;
    private final JwtUtil JWT_TOKEN_UTIL;
    private final UserController USER_CONTROLLER;


    public AuthenticationController(AuthenticationManager authentication_manager, UserDetailsDAO user_details_dao, JwtUtil jwt_token_util, UserController user_controller) {
        AUTHENTICATION_MANAGER = authentication_manager;
        USER_DETAILS_DAO = user_details_dao;
        JWT_TOKEN_UTIL = jwt_token_util;
        USER_CONTROLLER = user_controller;
    }

    @PostMapping
    public ResponseEntity<?> createAuthentication(@RequestBody AuthenticationRequest authenticationRequest) throws AuthenticationException {

        try {
            AUTHENTICATION_MANAGER.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException bce) {
            throw new BadCredentialsException("Bad credentials", bce);
        } catch (Exception e) {
            e.printStackTrace();
        }

        final UserDetails USERDETAILS = USER_DETAILS_DAO.loadUserByUsername(authenticationRequest.getUsername());
        final String JWT = JWT_TOKEN_UTIL.generateToken(USERDETAILS);

        User user = USER_CONTROLLER.getUser(USERDETAILS.getUsername());
        Role role = user.getRole();
        return ResponseEntity.ok(new AuthenticationResponse(user.getId(), role.getName(), JWT, JWT_TOKEN_UTIL.extractExpiration(JWT)));
    }
}
