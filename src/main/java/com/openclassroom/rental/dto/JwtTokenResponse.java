package com.openclassroom.rental.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JwtTokenResponse {

    private String token;
}
