package com.openclassroom.rental.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InputMessageDto extends MessageDto {

    @JsonProperty(value = "user_id")
    @Min(value = 1, message = "user id should be not null and superior to 1")
    private Long userId;

    @JsonProperty(value = "rental_id")
    @Min(value = 1, message = "rental id should be not null and superior to 1")
    private Long rentalId;
}
