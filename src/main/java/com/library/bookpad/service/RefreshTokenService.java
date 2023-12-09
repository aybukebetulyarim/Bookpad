package com.library.bookpad.service;

import com.library.bookpad.entity.RefreshToken;
import com.library.bookpad.entity.User;
import com.library.bookpad.repository.RefreshTokenRepository;
import com.library.bookpad.repository.UserRepository;
import com.library.bookpad.resource.request.authentication.RefreshTokenRequest;
import com.library.bookpad.resource.response.authentication.JwtResponse;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository, UserRepository userRepository, JwtService jwtService) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public RefreshToken createRefreshToken(String username) {
        User user = userRepository.findByUsername(username).get();
        RefreshToken refreshToken;
        Optional<RefreshToken> refreshTokenModel = refreshTokenRepository.findByUserId(user.getId());
        if (refreshTokenModel.isPresent()) {
            refreshToken = refreshTokenModel.get();
            refreshToken.setToken(UUID.randomUUID().toString());
            refreshToken.setExpiryDate(Instant.now().plusMillis(600000)); // 10 min
            refreshTokenRepository.save(refreshToken);
        } else {
            refreshToken = RefreshToken.builder()
                    .user(user)
                    .token(UUID.randomUUID().toString())
                    .expiryDate(Instant.now().plusMillis(600000)) // 10 min
                    .build();
            refreshTokenRepository.save(refreshToken);
        }
        return refreshToken;
    }

    public Optional<RefreshToken> getToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken verifyExpireTime(RefreshToken refreshToken) {
        if (refreshToken.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(refreshToken);
            throw new RuntimeException("Refresh token was expired. Please make a new login request.");
        }
        return refreshToken;
    }

    public JwtResponse updateRefreshToken(RefreshTokenRequest refreshTokenRequest) {
        Optional<RefreshToken> refreshToken = getToken(refreshTokenRequest.getToken());
        RefreshToken refreshToken1 = verifyExpireTime(refreshToken.get());
        User user = refreshToken1.getUser();
        String accessToken = jwtService.generateToken(user.getName());
        return JwtResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshTokenRequest.getToken())
                .build();

    }

    public Optional<RefreshToken> getRefreshToken(Long id) {
        return refreshTokenRepository.findById(id);
    }

    public void deleteRefreshToken(RefreshToken refreshToken) {
        refreshTokenRepository.delete(refreshToken);
    }
}
