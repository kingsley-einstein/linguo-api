package api.linguo.auth;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service(value = "userDetailsService")
public class UserDetailsServiceImplementation implements UserDetailsService {

    @Override
    public AuthUser loadUserByUsername(String username) {
        try {
            OAuthDao dao = new OAuthDao();

            AuthUser user = new AuthUser(dao.loadUserByUsername(username));

            return user;
        }
        catch (UsernameNotFoundException exception) {
            throw new UsernameNotFoundException("Unauthorized access");
        }
    }
}