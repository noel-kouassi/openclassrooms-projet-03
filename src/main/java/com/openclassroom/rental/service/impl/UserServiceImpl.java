package com.openclassroom.rental.service.impl;

import com.openclassroom.rental.dto.RegisterDto;
import com.openclassroom.rental.entity.User;
import com.openclassroom.rental.exception.RentalException;
import com.openclassroom.rental.repository.UserRepository;
import com.openclassroom.rental.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String saveUser(RegisterDto registerDto) {

        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new RentalException(HttpStatus.BAD_REQUEST, "This email is already in database");
        }
        User user = new User();
        user.setName(registerDto.getName());
        user.setEmail(registerDto.getEmail());
        user.setPassword(registerDto.getPassword());
        userRepository.save(user);
        return "User successfully created !";
    }
}
