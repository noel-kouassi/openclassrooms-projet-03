package com.openclassroom.rental.service;

import com.openclassroom.rental.dto.InputRentalDto;
import com.openclassroom.rental.dto.MessageDto;
import com.openclassroom.rental.dto.RentalDto;

public interface RentalService {

    MessageDto saveRental(InputRentalDto inputRentalDto);

    MessageDto updateRental(InputRentalDto inputRentalDto, Long rentalId);

    RentalDto getRental(Long rentalId);
}
