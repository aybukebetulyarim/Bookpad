package com.library.bookpad.resource.request.user;

import com.library.bookpad.entity.Role;
import lombok.Builder;

import java.util.Set;

@Builder
public record CreateUserRequest(
        String name,
        String username,
        String password,
        Set<Role> authorities
) {
}
