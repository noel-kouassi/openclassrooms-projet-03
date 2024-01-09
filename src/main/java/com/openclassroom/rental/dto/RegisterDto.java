package com.openclassroom.rental.dto;

import lombok.Data;

@Data
public class RegisterDto {

    private String email;

    private String name;

    private String password;
}
