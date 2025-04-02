package Service;

import Entity.UserEntity;
import Exception1.ResourceNotFoundException;
import Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<UserEntity> userEntity = userRepository.findByUsername(s);
        if (!userEntity.isPresent()) {
            throw new ResourceNotFoundException("Username not found");
        }
        return userEntity.get();
    }

    public List<SimpleGrantedAuthority> roles(String username){
        Optional<UserEntity> userEntity = userRepository.findUserWithRoles(username);
        if (!userEntity.isPresent()) {
            throw new BadCredentialsException("Invalid username");
        }

        List<String> roles = userEntity.get().getUserHasRoles().stream().map(e-> e.getRole().getName()).toList();
        return roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.toUpperCase())).toList();
    }
}
