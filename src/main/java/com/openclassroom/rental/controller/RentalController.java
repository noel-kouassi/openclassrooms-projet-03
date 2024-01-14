package com.openclassroom.rental.controller;

import com.openclassroom.rental.dto.InputRentalDto;
import com.openclassroom.rental.dto.MessageDto;
import com.openclassroom.rental.dto.RentalDto;
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

import java.util.List;

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
     * @param rentalId       the identifier of the rental to be updated
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
                                                   @Valid @RequestBody InputRentalDto inputRentalDto) {
        MessageDto messageDto = rentalService.updateRental(inputRentalDto, rentalId);
        return new ResponseEntity<>(messageDto, HttpStatus.OK);
    }

    /**
     * @param rentalId the identifier of the rental to be retrieved
     * @return rentalDto as response
     */
    @Operation(summary = "Get a rental",
            description = "getRental REST API is used to get a rental from the information system",
            responses = {@ApiResponse(responseCode = "200", description = "Rental provided with success"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized user, provided user credentials are wrong", content = @Content(mediaType = "*/*"))}
    )
    @GetMapping("/rentals/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<RentalDto> getRental(@PathVariable(value = "id") Long rentalId) {
        RentalDto rentalDto = rentalService.getRental(rentalId);
        return new ResponseEntity<>(rentalDto, HttpStatus.OK);
    }

    /**
     * @return rentalDtos as response
     */
    @Operation(summary = "Get all the rentals",
            description = "getRentals REST API is used to get all the rentals from the information system",
            responses = {@ApiResponse(responseCode = "200", description = "Rentals provided with success"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized user, provided user credentials are wrong", content = @Content(mediaType = "*/*"))}
    )
    @GetMapping("/rentals")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<RentalDto>> getRentals() {
        List<RentalDto> rentalDtos = rentalService.getAllRentals();
        return new ResponseEntity<>(rentalDtos, HttpStatus.OK);
    }
}
