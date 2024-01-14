package com.openclassroom.rental.entity;

import com.openclassroom.rental.common.AbstractEntity;
import com.openclassroom.rental.dto.InputRentalDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rentals")
public class Rental extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "surface")
    private BigDecimal surface;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "picture")
    private String picture;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User user;

    public void updateInformation(InputRentalDto inputRentalDto) {

        if (inputRentalDto.getName() != null) {
            this.name = inputRentalDto.getName();
        }

        if (inputRentalDto.getSurface() != null) {
            this.surface = inputRentalDto.getSurface();
        }

        if (inputRentalDto.getPrice() != null) {
            this.price = inputRentalDto.getPrice();
        }

        if (inputRentalDto.getPicture() != null) {
            this.picture = inputRentalDto.getPicture();
        }

        if (inputRentalDto.getDescription() != null) {
            this.description = inputRentalDto.getDescription();
        }
    }
}
