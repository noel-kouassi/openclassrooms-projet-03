package com.openclassroom.rental.service.impl;

import com.openclassroom.rental.dto.input.InputMessageDto;
import com.openclassroom.rental.dto.input.MessageDto;
import com.openclassroom.rental.entity.Message;
import com.openclassroom.rental.entity.Rental;
import com.openclassroom.rental.entity.User;
import com.openclassroom.rental.exception.ResourceNotFoundException;
import com.openclassroom.rental.repository.MessageRepository;
import com.openclassroom.rental.repository.RentalRepository;
import com.openclassroom.rental.repository.UserRepository;
import com.openclassroom.rental.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    private final UserRepository userRepository;

    private final RentalRepository rentalRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository, UserRepository userRepository, RentalRepository rentalRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.rentalRepository = rentalRepository;
    }

    @Override
    public MessageDto saveMessage(InputMessageDto inputMessageDto) {

        User user = userRepository.findById(inputMessageDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", inputMessageDto.getUserId()));

        Rental rental = rentalRepository.findById(inputMessageDto.getRentalId())
                .orElseThrow(() -> new ResourceNotFoundException("Rental", "id", inputMessageDto.getRentalId()));

        Message message = new Message();
        message.setMessage(inputMessageDto.getMessage());
        message.setUser(user);
        message.setRental(rental);
        messageRepository.save(message);
        return new MessageDto("Message send with success");
    }
}
