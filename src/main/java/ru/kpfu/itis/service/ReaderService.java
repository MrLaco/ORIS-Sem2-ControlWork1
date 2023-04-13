package ru.kpfu.itis.service;

import ru.kpfu.itis.dto.*;

public interface ReaderService {

    ReaderResponseDto getReaderByEmail(String email);
    ReaderResponseDto getReaderByPhoneNumber(String phoneNumber);
    ReaderResponseDto getReaderByFirstNameAndLastName(String firstName, String lastName);

    ReaderResponseDto create(ReaderRequestDto userDto, String url);
    boolean verify(String verificationCode);
    void sendVerificationMail(String mail, String name, String code, String url);
}
