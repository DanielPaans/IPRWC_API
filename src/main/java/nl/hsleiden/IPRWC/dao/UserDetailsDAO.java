package nl.hsleiden.IPRWC.dao;

import nl.hsleiden.IPRWC.models.*;
import nl.hsleiden.IPRWC.repositories.AdminRepository;
import nl.hsleiden.IPRWC.repositories.LoggedInUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
public class UserDetailsDAO implements UserDetailsService {

    private final AdminRepository ADMIN_REPOSITORY;
    private final LoggedInUserRepository USER_REPOSITORY;

    public UserDetailsDAO(AdminRepository admin_repository, LoggedInUserRepository user_repository) {
        ADMIN_REPOSITORY = admin_repository;
        USER_REPOSITORY = user_repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (isValidUUID(username)) {
            Optional<LoggedInUser> loggedInUser = USER_REPOSITORY.findLoggedInUserByUsername(username);
            loggedInUser.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));
            return loggedInUser.map(LoggedInUserDetails::new).get();
        } else {
            try {
                Optional<Admin> admin = ADMIN_REPOSITORY.findAdminByUsername(username);
                admin.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));
                return admin.map(AdminDetails::new).get();
            } catch (IllegalArgumentException iae) {
                throw new UsernameNotFoundException("Not the right authority");
            }
        }
    }

    private boolean isValidUUID(String uuid) {
        final Pattern UUID_REGEX_PATTERN =
                Pattern.compile("^[{]?[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}[}]?$");

        if (uuid == null) {return false;}
        return UUID_REGEX_PATTERN.matcher(uuid).matches();
    }
}