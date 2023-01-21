package com.library.bookpad.model;

public class DeleteUserResponse {
    private String message;

    public DeleteUserResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
