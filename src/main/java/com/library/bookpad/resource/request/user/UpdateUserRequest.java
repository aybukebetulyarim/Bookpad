package com.library.bookpad.resource.request.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateUserRequest {
    private String username;
    private String name;
    private String password;
}
