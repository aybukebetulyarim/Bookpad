package com.library.bookpad.resource.response.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class GetUserResponse extends UserResponse {
    public static GetUserResponse build(UserResponse userResponse) {
        return GetUserResponse.builder().id(userResponse.getId())
                .name(userResponse.getName())
                .password(userResponse.getPassword())
                .username(userResponse.getUsername())
                .build();
    }
}
