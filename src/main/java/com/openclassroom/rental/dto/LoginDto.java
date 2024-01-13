package com.openclassroom.rental.dto;

import lombok.*;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginDto {

    @NotBlank(message = "Login should not be null or empty")
    @Email(message = "Login should be an email format")
    private String login;

    @NotBlank(message = "Password should not be null or empty")
    private String password;
}
