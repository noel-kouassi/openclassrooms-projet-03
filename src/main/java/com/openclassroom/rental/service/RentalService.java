package com.openclassroom.rental.service;

import com.openclassroom.rental.dto.InputRentalDto;
import com.openclassroom.rental.dto.MessageDto;

public interface RentalService {

    MessageDto saveRental(InputRentalDto inputRentalDto);
}
