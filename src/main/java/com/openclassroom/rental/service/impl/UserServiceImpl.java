package com.openclassroom.rental.service.impl;

import com.openclassroom.rental.dto.RegisterDto;
import com.openclassroom.rental.dto.UserDto;
import com.openclassroom.rental.entity.User;
import com.openclassroom.rental.repository.UserRepository;
import com.openclassroom.rental.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper){
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto saveUser(RegisterDto registerDto) {
        User user = modelMapper.map(registerDto, User.class);
        User userSaved = userRepository.save(user);
        return modelMapper.map(userSaved, UserDto.class);
    }
}
