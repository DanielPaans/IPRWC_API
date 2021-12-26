package nl.hsleiden.IPRWC.dao;

import nl.hsleiden.IPRWC.models.*;
import nl.hsleiden.IPRWC.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserDetailsDAO implements UserDetailsService {

    private final UserRepository USER_REPOSITORY;

    public UserDetailsDAO(UserRepository userRepository) {
        this.USER_REPOSITORY = userRepository;
    }

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Optional<User> user = USER_REPOSITORY.findUserByUsername(username);
            user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));
            return user.map(UserDetails::new).get();
        } catch (IllegalArgumentException iae) {
            throw new UsernameNotFoundException("Not the right authority");
        }
    }

    private boolean isValidUUID(String uuid) {
        final Pattern UUID_REGEX_PATTERN =
                Pattern.compile("^[{]?[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}[}]?$");

        if (uuid == null) {return false;}
        return UUID_REGEX_PATTERN.matcher(uuid).matches();
    }
}