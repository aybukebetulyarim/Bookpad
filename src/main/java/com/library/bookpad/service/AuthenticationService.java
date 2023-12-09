package com.library.bookpad.service;


import com.library.bookpad.entity.RefreshToken;
import com.library.bookpad.entity.User;
import com.library.bookpad.repository.UserRepository;
import com.library.bookpad.resource.request.authentication.AuthenticationRequest;
import com.library.bookpad.resource.request.user.CreateUserRequest;
import com.library.bookpad.resource.response.authentication.JwtResponse;
import com.library.bookpad.util.PasswordEncoderConfig;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoderConfig passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;

    public AuthenticationService(UserRepository userRepository,
                                 PasswordEncoderConfig passwordEncoder,
                                 AuthenticationManager authenticationManager,
                                 JwtService jwtService,
                                 RefreshTokenService refreshTokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
    }

    public String createUser(CreateUserRequest request) {
        User newUser = User.builder()
                .name(request.name())
                .username(request.username())
                .password(passwordEncoder.passwordEncoder().encode(request.password()))
                .authorities(request.authorities())
                .accountNonExpired(true)
                .isEnabled(true)
                .isCredentialsNonExpired(true)
                .accountNonLocked(true)
                .build();

        userRepository.save(newUser);
        return "User saved successfully.";
    }


    public JwtResponse loginUser(AuthenticationRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        if (authentication.isAuthenticated()) {
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(request.username());
            String jwtToken = jwtService.generateToken(request.username());
            return JwtResponse.builder().refreshToken(refreshToken.getToken()).accessToken(jwtToken).build();
        }
        throw new UsernameNotFoundException("invalid user");
    }

    public Optional<User> getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        return user.orElseThrow(EntityNotFoundException::new);
    }
}
