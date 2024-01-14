package com.openclassroom.rental.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserDto {

    private Long id;

    private String name;

    private String email;

    @JsonProperty(value = "created_at")
    private String createdAt;

    @JsonProperty(value = "updated_at")
    private String updatedAt;
}
