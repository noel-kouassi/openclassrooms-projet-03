package com.openclassroom.rental.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {

    @NotBlank(message = "Message should not be null or empty")
    private String message;
}
