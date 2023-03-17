package ru.kpfu.itis.service;

import ru.kpfu.itis.dto.*;

public interface ReaderService {

    ReaderResponseDto getReaderByEmail(String email);
    ReaderResponseDto getReaderByPhoneNumber(String phoneNumber);
    ReaderResponseDto getReaderByFirstNameAndLastName(String firstName, String lastName);
}
