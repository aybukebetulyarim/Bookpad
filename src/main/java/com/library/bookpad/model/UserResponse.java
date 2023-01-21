package com.library.bookpad.model;

public class UserResponse {
    public User data;
    public String message;

    public UserResponse(User data, String message) {
        this.data = data;
        this.message = message;
    }
}
