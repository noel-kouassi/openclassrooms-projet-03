package com.openclassroom.rental.dto.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto extends LoginDto {

    @NotBlank(message = "Name should not be null or empty")
    private String name;
}
