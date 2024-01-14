package com.openclassroom.rental.service;

import com.openclassroom.rental.dto.InputMessageDto;
import com.openclassroom.rental.dto.MessageDto;

public interface MessageService {

    MessageDto saveMessage(InputMessageDto inputMessageDto);
}
