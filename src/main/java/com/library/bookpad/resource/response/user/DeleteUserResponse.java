package com.library.bookpad.resource.response.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class DeleteUserResponse extends UserResponse {
    private String message;

    public static DeleteUserResponse build(UserResponse userResponse, String message) {
        return DeleteUserResponse.builder()
                .id(userResponse.getId())
                .name(userResponse.getName())
                .password(userResponse.getPassword())
                .username(userResponse.getUsername())
                .message(message)
                .build();
    }

    public static DeleteUserResponse build(String message) {
        return DeleteUserResponse.builder()
                .message(message)
                .build();
    }
}
