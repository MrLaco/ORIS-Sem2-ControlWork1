package ru.kpfu.itis.service.impl;

import lombok.*;
import org.springframework.stereotype.*;
import ru.kpfu.itis.dto.*;
import ru.kpfu.itis.model.*;
import ru.kpfu.itis.repository.*;
import ru.kpfu.itis.service.*;

import java.util.*;

@Service
@AllArgsConstructor
public class ReaderServiceImpl implements ReaderService {

    private final ReaderRepository readerRepository;

    @Override
    public ReaderResponseDto getReaderByEmail(String email) {

        Optional<Reader> reader = readerRepository.getReaderByEmail(email);

        if (reader.isPresent()) {
            return ReaderResponseDto.fromReader(
                    reader.get()
            );
        } else {
            throw new RuntimeException("Такого ридера не существует!");
        }
    }

    @Override
    public ReaderResponseDto getReaderByPhoneNumber(String phoneNumber) {

        return ReaderResponseDto.fromReader(
                readerRepository.getReaderByPhoneNumber(phoneNumber)
        );
    }

    @Override
    public ReaderResponseDto getReaderByFirstNameAndLastName(String firstName, String lastName) {

        return ReaderResponseDto.fromReader(
                readerRepository.getReaderByFirstNameAndLastName(firstName, lastName)
        );
    }
}
