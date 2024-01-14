package com.openclassroom.rental.dto;

import jakarta.validation.constraints.Min;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.math.BigDecimal;

@Data
public class InputRentalDto {

    @NotBlank(message = "Name should not be null or empty")
    private String name;

    @Min(value = 0)
    private BigDecimal surface;

    @Min(value = 0)
    private BigDecimal price;

    @NotBlank(message = "Picture should not be null or empty")
    private String picture;

    private String description;

    private Long ownerId;
}
