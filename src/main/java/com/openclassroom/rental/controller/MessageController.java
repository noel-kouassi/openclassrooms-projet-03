package com.openclassroom.rental.controller;

import com.openclassroom.rental.dto.input.InputMessageDto;
import com.openclassroom.rental.dto.input.MessageDto;
import com.openclassroom.rental.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Tag(name = "Message rest api controller for message operation")
@SecurityRequirement(name = "rentalapi")
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    /**
     * @param inputMessageDto the input from which the message is created
     * @return messageDto as response
     */
    @Operation(summary = "Create a message",
            description = "createMessage REST API is used to save a message into the information system",
            responses = {@ApiResponse(responseCode = "200", description = "Message created with success"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized user, provided user credentials are wrong", content = @Content(mediaType = "*/*"))}
    )
    @PostMapping("/messages")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<MessageDto> createMessage(@Valid @RequestBody InputMessageDto inputMessageDto) {
        MessageDto messageDto = messageService.saveMessage(inputMessageDto);
        return new ResponseEntity<>(messageDto, HttpStatus.OK);
    }
}
