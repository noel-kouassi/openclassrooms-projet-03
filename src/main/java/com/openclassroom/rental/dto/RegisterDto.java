package com.openclassroom.rental.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterDto {

    @NotBlank(message = "Email should not be null or empty")
    @Email(message = "Input should be an email format")
    private String email;

    @NotBlank(message = "Name should not be null or empty")
    private String name;

    @NotBlank(message = "Password should not be null or empty")
    private String password;

}
