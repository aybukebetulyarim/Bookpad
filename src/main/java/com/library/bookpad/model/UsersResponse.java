package com.library.bookpad.model;

import java.util.ArrayList;
import java.util.List;

public class UsersResponse {
    public List<User> data;
    public String message;
    public int size;

    public UsersResponse(List<User> data, String message, int size) {
        this.data = data;
        this.message = message;
        this.size = size;
    }
}
