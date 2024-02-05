package com.openclassroom.rental.service;

import com.openclassroom.rental.dto.input.InputRentalDto;
import com.openclassroom.rental.dto.input.MessageDto;
import com.openclassroom.rental.dto.response.RentalDto;
import com.openclassroom.rental.dto.response.RentalResponse;

public interface RentalService {

    MessageDto saveRental(InputRentalDto inputRentalDto);

    MessageDto updateRental(InputRentalDto inputRentalDto, Long rentalId);

    RentalDto getRental(Long rentalId);

    RentalResponse getAllRentals();
}
