package com.library.bookpad.controller;

import com.library.bookpad.base.ResponseHandler;
import com.library.bookpad.resource.request.user.DeleteUserRequest;
import com.library.bookpad.resource.request.user.GetUserRequest;
import com.library.bookpad.resource.request.user.UpdateUserRequest;
import com.library.bookpad.resource.response.BaseResponse;
import com.library.bookpad.resource.response.user.DeleteUserResponse;
import com.library.bookpad.resource.response.user.GetUserResponse;
import com.library.bookpad.resource.response.user.UpdateUserResponse;
import com.library.bookpad.resource.response.user.UserResponse;
import com.library.bookpad.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public BaseResponse<List<UserResponse>> getUsers() {
        return ResponseHandler.responseEntityWrapper(HttpStatus.OK, userService.getUsers());
    }

    @GetMapping("/{id}")
    public BaseResponse<GetUserResponse> getUser(@PathVariable(value = "id") Long userId) {
        return ResponseHandler.responseEntityWrapper(HttpStatus.OK, userService.getUser(GetUserRequest.builder().userId(userId).build()));
    }

    @DeleteMapping
    public BaseResponse<DeleteUserResponse> deleteUser(@Valid @RequestBody DeleteUserRequest deleteUserRequest) {
        return ResponseHandler.responseEntityWrapper(HttpStatus.OK, userService.deleteUser(deleteUserRequest));
    }

    @PutMapping("/{id}")
    public BaseResponse<UpdateUserResponse> updateUser(@PathVariable(value = "id") Long userId,
                                          @Valid @RequestBody UpdateUserRequest updateUserRequest) {
        return ResponseHandler.responseEntityWrapper(HttpStatus.OK, userService.updateUser(userId, updateUserRequest));
    }

}
