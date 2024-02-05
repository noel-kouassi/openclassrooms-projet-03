package com.openclassroom.rental.service;

import com.openclassroom.rental.dto.input.InputMessageDto;
import com.openclassroom.rental.dto.input.MessageDto;

public interface MessageService {

    MessageDto saveMessage(InputMessageDto inputMessageDto);
}
