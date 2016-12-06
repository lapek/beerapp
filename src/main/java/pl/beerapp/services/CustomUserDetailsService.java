package pl.beerapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.beerapp.entities.UserRole;
import pl.beerapp.repositories.UserRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        pl.beerapp.entities.User user = userRepository.findByUsername(username);
        List<GrantedAuthority> authorities = buildUserAuthority(user.getRoles());
        return buildUserForAuthentication(user, authorities);
    }

    private User buildUserForAuthentication(pl.beerapp.entities.User user,
                                            List<GrantedAuthority> authorities) {
        return new User(user.getUsername(), user.getPassword(), authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {
        Set<GrantedAuthority> setAuths = new HashSet<>();
        setAuths.addAll(userRoles.stream().map(userRole -> new SimpleGrantedAuthority(userRole.getRoleName()))
                .collect(Collectors.toList()));
        return new ArrayList<>(setAuths);
    }
}
