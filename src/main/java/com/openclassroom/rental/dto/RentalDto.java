package com.openclassroom.rental.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.openclassroom.rental.util.DateFormatter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RentalDto {

    private Long id;

    private String name;

    private BigDecimal surface;

    private BigDecimal price;

    private String picture;

    private String description;

    @JsonProperty(value = "owner_id")
    private Long ownerId;

    @JsonProperty(value = "created_at")
    private String createdAt;

    @JsonProperty(value = "updated_at")
    private String updatedAt;

    public void formatRentalDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date parsedCreatedAtDate = dateFormat.parse(createdAt);
            Date parsedUpdatedAtDate = dateFormat.parse(updatedAt);

            Timestamp timestampCreatedAt = new Timestamp(parsedCreatedAtDate.getTime());
            Timestamp timestampUpdatedAt = new Timestamp(parsedUpdatedAtDate.getTime());

            this.createdAt = DateFormatter.formatDate(timestampCreatedAt);
            this.updatedAt = DateFormatter.formatDate(timestampUpdatedAt);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
