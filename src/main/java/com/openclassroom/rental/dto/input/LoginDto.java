package com.openclassroom.rental.dto.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    @NotBlank(message = "Login should not be null or empty")
    @Email(message = "Login should be an email format")
    private String email;

    @NotBlank(message = "Password should not be null or empty")
    private String password;
}
