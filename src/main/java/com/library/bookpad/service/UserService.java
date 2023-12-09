package com.library.bookpad.service;

import com.library.bookpad.common.BookpadException;
import com.library.bookpad.entity.RefreshToken;
import com.library.bookpad.entity.User;
import com.library.bookpad.enums.ExceptionEnum;
import com.library.bookpad.mapper.UserMapper;
import com.library.bookpad.repository.UserRepository;
import com.library.bookpad.resource.request.user.DeleteUserRequest;
import com.library.bookpad.resource.request.user.GetUserRequest;
import com.library.bookpad.resource.request.user.UpdateUserRequest;
import com.library.bookpad.resource.response.user.DeleteUserResponse;
import com.library.bookpad.resource.response.user.GetUserResponse;
import com.library.bookpad.resource.response.user.UpdateUserResponse;
import com.library.bookpad.resource.response.user.UserResponse;
import com.library.bookpad.util.PasswordEncoderConfig;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RefreshTokenService refreshTokenService;
    private final PasswordEncoderConfig passwordEncoder;

    private final UserMapper mapper = UserMapper.INSTANCE;

    public UserService(UserRepository userRepository, RefreshTokenService refreshTokenService, PasswordEncoderConfig passwordEncoder) {
        this.userRepository = userRepository;
        this.refreshTokenService = refreshTokenService;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserResponse> getUsers() {
        return mapper.entityListToResponseList(userRepository.findAll());
    }

    public GetUserResponse getUser(GetUserRequest request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new BookpadException(UserService.class, ExceptionEnum.USER_NOT_FOUND));
        UserResponse mappedUserResponse = mapper.entityToResponse(user);
        return GetUserResponse.build(mappedUserResponse);
    }

    public DeleteUserResponse deleteUser(DeleteUserRequest request) {
        Optional<User> user = userRepository.findById(request.getUserId());
        if (user.isPresent()) {
            Optional<RefreshToken> refreshToken = refreshTokenService.getRefreshToken(user.get().getId());
            refreshTokenService.deleteRefreshToken(refreshToken.orElseThrow(() -> new BookpadException(UserService.class, ExceptionEnum.USER_NOT_FOUND)));
            userRepository.deleteById(request.getUserId());
            UserResponse mappedUserResponse = mapper.entityToResponse(user.get());
            return DeleteUserResponse.build(mappedUserResponse, "User deleted successfully");
        }
        return DeleteUserResponse.builder().message("User not found, so user did not deleted.").build();
    }

    public UpdateUserResponse updateUser(Long userId, UpdateUserRequest request) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()) {
            user.get().setUsername(request.getUsername());
            user.get().setName(request.getName());
            user.get().setPassword(passwordEncoder.passwordEncoder().encode(request.getPassword()));
            userRepository.save(user.get());
            UserResponse mappedUserResponse = mapper.entityToResponse(user.get());
            return UpdateUserResponse.build(mappedUserResponse, "User updated successfully");
        }
        return UpdateUserResponse.build("User didn't update because user not found");
    }
}
