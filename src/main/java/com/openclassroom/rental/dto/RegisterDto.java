package com.openclassroom.rental.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {

    @NotBlank(message = "Email should not be null or empty")
    @Email(message = "Input should be an email format")
    private String email;

    @NotBlank(message = "Name should not be null or empty")
    private String name;

    @NotBlank(message = "Password should not be null or empty")
    @JsonIgnore
    private String password;
}
