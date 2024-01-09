package com.openclassroom.rental.dto;

import lombok.Data;

@Data
public class UserDto extends RegisterDto {

    private Long id;

    private String createdAt;

    private String updatedAt;
}
