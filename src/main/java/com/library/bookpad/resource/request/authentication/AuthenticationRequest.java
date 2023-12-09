package com.library.bookpad.resource.request.authentication;

public record AuthenticationRequest(
        String username,
        String password
) {
}
