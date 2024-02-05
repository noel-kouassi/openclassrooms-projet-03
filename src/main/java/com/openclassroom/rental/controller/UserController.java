package com.openclassroom.rental.controller;

import com.openclassroom.rental.dto.response.UserDto;
import com.openclassroom.rental.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Tag(name = "User rest api controller for user operation")
@SecurityRequirement(name = "rentalapi")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * @param userId the identifier of the user to be retrieved
     * @return userDto as response
     */
    @Operation(summary = "Get a user",
            description = "getUser REST API is used to get a user from the information system",
            responses = {@ApiResponse(responseCode = "200", description = "User provided with success"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized user, provided user credentials are wrong", content = @Content(mediaType = "*/*"))}
    )
    @GetMapping("/user/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserDto> getUser(@PathVariable(value = "id") Long userId) {
        UserDto userDto = userService.getUserById(userId);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}
