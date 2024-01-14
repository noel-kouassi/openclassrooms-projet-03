package com.openclassroom.rental.controller;

import com.openclassroom.rental.dto.InputRentalDto;
import com.openclassroom.rental.dto.MessageDto;
import com.openclassroom.rental.service.RentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Tag(name = "Rental rest api controller for operations management")
public class RentalController {

    private final RentalService rentalService;

    @Autowired
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    /**
     * @param inputRentalDto the input from which the rental is created
     * @return messageDto as response
     */
    @Operation(summary = "Create a rental",
            description = "createRental REST API is used to save a rental into the information system",
            responses = {@ApiResponse(responseCode = "200", description = "Rental created with success"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized user, provided user credentials are wrong", content = @Content(mediaType = "*/*"))}
    )
    @PostMapping("/rentals")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<MessageDto> createRental(@Valid @RequestBody InputRentalDto inputRentalDto) {
        MessageDto messageDto = rentalService.saveRental(inputRentalDto);
        return new ResponseEntity<>(messageDto, HttpStatus.OK);
    }

    /**
     * @param inputRentalDto the input from which the rental is updated
     * @param rentalId the identifier of the rental to be updated
     * @return messageDto as response
     */
    @Operation(summary = "Update a rental",
            description = "updateRental REST API is used to update a rental into the information system",
            responses = {@ApiResponse(responseCode = "200", description = "Rental updated with success"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized user, provided user credentials are wrong", content = @Content(mediaType = "*/*"))}
    )
    @PutMapping("/rentals/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<MessageDto> updateRental(@PathVariable(value = "id") Long rentalId,
                                                    @Valid @RequestBody InputRentalDto inputRentalDto){
        MessageDto messageDto = rentalService.updateRental(inputRentalDto, rentalId);
        return new ResponseEntity<>(messageDto, HttpStatus.OK);
    }
}