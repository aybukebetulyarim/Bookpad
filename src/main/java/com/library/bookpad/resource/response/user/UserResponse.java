package com.library.bookpad.resource.response.user;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class UserResponse {
    private Long id;
    private String username;
    private String name;
    private String password;
}
