package com.library.bookpad.controller;


import com.library.bookpad.base.ResponseHandler;
import com.library.bookpad.resource.request.authentication.AuthenticationRequest;
import com.library.bookpad.resource.request.authentication.RefreshTokenRequest;
import com.library.bookpad.resource.request.user.CreateUserRequest;
import com.library.bookpad.resource.response.BaseResponse;
import com.library.bookpad.resource.response.authentication.JwtResponse;
import com.library.bookpad.service.AuthenticationService;
import com.library.bookpad.service.JwtService;
import com.library.bookpad.service.RefreshTokenService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;

    public AuthenticationController(AuthenticationService authenticationService, AuthenticationManager authenticationManager, JwtService jwtService, RefreshTokenService refreshTokenService) {
        this.authenticationService = authenticationService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Hello World";
    }

    @PostMapping("/register")
    public BaseResponse<String> register(@RequestBody CreateUserRequest request) {
        return ResponseHandler.responseEntityWrapper(HttpStatus.OK, authenticationService.createUser(request));
    }

    @PostMapping("/login")
    public BaseResponse<JwtResponse> login(@RequestBody @Valid AuthenticationRequest request) {
        return ResponseHandler.responseEntityWrapper(HttpStatus.OK, authenticationService.loginUser(request));
    }

    @PostMapping("/refreshToken")
    public BaseResponse<JwtResponse> refreshToken(@RequestBody @Valid RefreshTokenRequest refreshTokenRequest) {
        return ResponseHandler.responseEntityWrapper(HttpStatus.OK, refreshTokenService.updateRefreshToken(refreshTokenRequest));
    }

    @GetMapping("/user")
    public String getUserString() {
        return "This is user";
    }

    @GetMapping("/admin")
    public String getAdmin() {
        return "thıs is admin";
    }
}
