package com.openclassroom.rental.controller;

import com.openclassroom.rental.dto.input.LoginDto;
import com.openclassroom.rental.dto.input.RegisterDto;
import com.openclassroom.rental.dto.response.JwtTokenResponse;
import com.openclassroom.rental.dto.response.UserDto;
import com.openclassroom.rental.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Tag(name = "Authentication rest api controller for user identification")
public class AuthenticationController {

    private final UserService userService;

    @Autowired
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    /**
     * @param registerDto input data which contains the user information for his creation
     * @return Jwt as token response
     */
    @Operation(summary = "Create an new user",
            description = "Create User REST API is used to save user into database",
            responses = {@ApiResponse(responseCode = "200", description = "User created with success"),
                    @ApiResponse(responseCode = "400", description = "Bad request, wrong email or password", content = @Content(mediaType = "*/*"))}
    )
    @PostMapping("/auth/register")
    public ResponseEntity<JwtTokenResponse> registerUser(@Valid @RequestBody RegisterDto registerDto) {

        userService.saveUser(registerDto);
        LoginDto loginDto = new LoginDto();
        loginDto.setEmail(registerDto.getEmail());
        loginDto.setPassword(registerDto.getPassword());

        String userToken = userService.login(loginDto);
        JwtTokenResponse jwtTokenResponse = new JwtTokenResponse();
        jwtTokenResponse.setToken(userToken);

        return new ResponseEntity<>(jwtTokenResponse, HttpStatus.OK);
    }

    /**
     * @param loginDto input data which contains the user credentials for his connection
     * @return Jwt as token response
     */
    @Operation(summary = "Connect an existing user",
            description = "Login User REST API is used to provide token jwt to an existing user",
            responses = {@ApiResponse(responseCode = "200", description = "User connected with success"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized user, provided user credentials are wrong", content = @Content(mediaType = "*/*"))}
    )
    @PostMapping("/auth/login")
    public ResponseEntity<JwtTokenResponse> loginUser(@Valid @RequestBody LoginDto loginDto) {
        String userToken = userService.login(loginDto);
        JwtTokenResponse jwtTokenResponse = new JwtTokenResponse();
        jwtTokenResponse.setToken(userToken);
        return new ResponseEntity<>(jwtTokenResponse, HttpStatus.OK);
    }

    /**
     * @param request the httpServletRequest which from token is extracted
     * @return userDto as response
     */
    @Operation(summary = "Provide an user information",
            description = "GetUserFromToken REST API is used to provide user information just from token",
            responses = {@ApiResponse(responseCode = "200", description = "User information provided with success"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized user, provided user credentials are wrong", content = @Content(mediaType = "*/*"))}
    )
    @GetMapping("/auth/me")
    @SecurityRequirement(name = "rentalapi")
    public ResponseEntity<UserDto> getUserFromToken(HttpServletRequest request) {
        UserDto userDto = userService.getUserFromToken(request);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

}
