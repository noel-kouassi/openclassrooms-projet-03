package com.openclassroom.rental.controller;

import com.openclassroom.rental.dto.input.InputRentalDto;
import com.openclassroom.rental.dto.input.MessageDto;
import com.openclassroom.rental.dto.response.RentalDto;
import com.openclassroom.rental.dto.response.RentalResponse;
import com.openclassroom.rental.service.RentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import static java.lang.Long.parseLong;

@RestController
@RequestMapping("/api")
@Tag(name = "Rental rest api controller for operations management")
@SecurityRequirement(name = "rentalapi")
public class RentalController {

    private final RentalService rentalService;

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    /**
     * @param picture     the picture of the rental to be uploaded
     * @param price       the price of the rental
     * @param name        the name of the rental
     * @param description the description of the rental
     * @param surface     the surface of the rental
     * @param ownerId     the owner of the rental
     * @return messageDto as response
     */
    @Operation(summary = "Create a rental",
            description = "createRental REST API is used to save a rental into the information system",
            responses = {@ApiResponse(responseCode = "200", description = "Rental created with success"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized user, provided user credentials are wrong", content = @Content(mediaType = "*/*"))}
    )
    @PostMapping("/rentals")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<MessageDto> createRental(@RequestParam("picture") MultipartFile picture,
                                                   @RequestParam("price") BigDecimal price,
                                                   @RequestParam("name") String name,
                                                   @RequestParam("description") String description,
                                                   @RequestParam("surface") BigDecimal surface,
                                                   @RequestParam("owner_id") String ownerId) {
        InputRentalDto inputRentalDto;
        try {
            String fileName = picture.getOriginalFilename();
            String fileNamePath = "assets/" + fileName;
            Path filePath = Path.of(uploadPath + File.separator + fileName);
            Files.copy(picture.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            inputRentalDto = new InputRentalDto(name, surface, price, fileNamePath, description, parseLong(ownerId));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MessageDto messageDto = rentalService.saveRental(inputRentalDto);
        return new ResponseEntity<>(messageDto, HttpStatus.OK);
    }

    /**
     * @param price       the price of the rental
     * @param name        the name of the rental
     * @param description the description of the rental
     * @param surface     the surface of the rental
     * @param rentalId    the identifier of the rental to be updated
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
                                                   @RequestParam("price") BigDecimal price,
                                                   @RequestParam("name") String name,
                                                   @RequestParam("description") String description,
                                                   @RequestParam("surface") BigDecimal surface) {
        InputRentalDto inputRentalDto = new InputRentalDto();
        inputRentalDto.setPrice(price);
        inputRentalDto.setName(name);
        inputRentalDto.setDescription(description);
        inputRentalDto.setSurface(surface);
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
    public ResponseEntity<RentalResponse> getRentals() {
        RentalResponse rentalResponse = rentalService.getAllRentals();
        return new ResponseEntity<>(rentalResponse, HttpStatus.OK);
    }
}
