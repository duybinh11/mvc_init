package Service;

import DTO.Request.UserRequest;

import DTO.Response.TokenResponse;
import Entity.UserEntity;
import Repository.UserRepository;
import Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailService userDetailService;


    public TokenResponse login(UserRequest userRequest) {
        try {

            List<SimpleGrantedAuthority> authoritiesUser =  userDetailService.roles(userRequest.getUsername());
            System.out.println(authoritiesUser);
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userRequest.getUsername(),
                            userRequest.getPassword(),
                            authoritiesUser
                    )
            );


            String token = jwtUtil.generateToken(userRequest.getUsername(), authoritiesUser);
            String refreshToken = jwtUtil.generateRefreshToken(userRequest.getUsername(), authoritiesUser);

            return TokenResponse.builder().token(token).refreshToken(refreshToken).build();


        } catch (BadCredentialsException ex) {
            throw ex;
        }
    }
}
