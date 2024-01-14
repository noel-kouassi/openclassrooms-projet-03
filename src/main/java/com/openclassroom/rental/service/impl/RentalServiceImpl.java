package com.openclassroom.rental.service.impl;

import com.openclassroom.rental.dto.InputRentalDto;
import com.openclassroom.rental.dto.MessageDto;
import com.openclassroom.rental.dto.RentalDto;
import com.openclassroom.rental.entity.Rental;
import com.openclassroom.rental.entity.User;
import com.openclassroom.rental.exception.ResourceNotFoundException;
import com.openclassroom.rental.repository.RentalRepository;
import com.openclassroom.rental.repository.UserRepository;
import com.openclassroom.rental.service.RentalService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class RentalServiceImpl implements RentalService {

    private final RentalRepository rentalRepository;

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public RentalServiceImpl(RentalRepository rentalRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.rentalRepository = rentalRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public MessageDto saveRental(InputRentalDto inputRentalDto) {

        User user = userRepository.findById(inputRentalDto.getOwnerId())
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User not found with ownerId: %s", inputRentalDto.getOwnerId())));

        Rental rental = new Rental();
        rental.setName(inputRentalDto.getName());
        rental.setDescription(inputRentalDto.getDescription());
        rental.setPicture(inputRentalDto.getPicture());
        rental.setPrice(inputRentalDto.getPrice());
        rental.setSurface(inputRentalDto.getSurface());

        rental.setUser(user);
        rentalRepository.save(rental);
        return new MessageDto("Rental created !");
    }

    @Override
    public MessageDto updateRental(InputRentalDto inputRentalDto, Long rentalId) {

        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new ResourceNotFoundException("Rental", "id", rentalId));
        rental.updateInformation(inputRentalDto);
        rentalRepository.save(rental);
        return new MessageDto("Rental updated !");
    }

    @Override
    public RentalDto getRental(Long rentalId) {

        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new ResourceNotFoundException("Rental", "id", rentalId));

        RentalDto rentalDto = modelMapper.map(rental, RentalDto.class);
        User user = rental.getUser();
        if (user != null) {
            rentalDto.setOwnerId(user.getId());
        }
        rentalDto.formatRentalDate();
        return rentalDto;
    }
}
