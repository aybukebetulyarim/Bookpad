package com.library.bookpad.resource.response.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class UpdateUserResponse extends UserResponse {
    public String updatedUserInfo;

    public static UpdateUserResponse build(UserResponse userResponse, String updatedUserInfo) {
        return UpdateUserResponse.builder()
                .id(userResponse.getId())
                .name(userResponse.getName())
                .password(userResponse.getPassword())
                .username(userResponse.getUsername())
                .updatedUserInfo(updatedUserInfo)
                .build();
    }

    public static UpdateUserResponse build(String updatedUserInfo) {
        return UpdateUserResponse.builder()
                .updatedUserInfo(updatedUserInfo)
                .build();
    }
}
