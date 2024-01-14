package com.openclassroom.rental.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"id", "name", "email", "created_at", "updated_at"})
public class UserDto extends RegisterDto {

    private Long id;

    @JsonProperty(value = "created_at")
    private String createdAt;

    @JsonProperty(value = "updated_at")
    private String updatedAt;
}
