package ru.kpfu.itis.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.dto.*;
import ru.kpfu.itis.exceptions.*;
import ru.kpfu.itis.model.*;
import ru.kpfu.itis.repository.*;
import ru.kpfu.itis.service.*;
import ru.kpfu.itis.service.impl.*;

import java.util.*;

@RestController
public class ReaderController {

    private final ReaderService readerService;
    private final ReaderRepository readerRepository;

    @Autowired
    public ReaderController(ReaderServiceImpl readerService, ReaderRepository readerRepository) {
        this.readerService = readerService;
        this.readerRepository = readerRepository;
    }

    @GetMapping("/service/reader/create")
    public boolean createReader(
            @RequestParam Optional<String> address,
            @RequestParam Optional<String> firstName,
            @RequestParam Optional<String> lastName,
            @RequestParam Optional<String> phoneNumber
    ) {
        if (address.isPresent() && firstName.isPresent() && lastName.isPresent() && phoneNumber.isPresent()) {
            Reader reader = Reader.builder()
                    .address(address.get())
                    .address(firstName.get())
                    .address(lastName.get())
                    .address(phoneNumber.get())
                    .build();
            readerRepository.save(reader);

            return true;
        } else {
            return false;
        }
    }

    @GetMapping("/service/reader/get")
    public ReaderResponseDto getReaderByPhoneNumberOrByFirstNameAndLastName(
            @RequestParam Optional<String> phoneNumber,
            @RequestParam Optional<String> firstName,
            @RequestParam Optional<String> lastName
    ) throws InvalidArgumentException {

        if (phoneNumber.isPresent() && firstName.isPresent() && lastName.isPresent()) {
            throw new InvalidArgumentException("Ты чорт, вводи правильные аргументы в query");
        }
        else if (phoneNumber.isPresent()) {
            return readerService.getReaderByPhoneNumber(phoneNumber.get());
        } else if (firstName.isPresent() && lastName.isPresent()) {
            return readerService.getReaderByFirstNameAndLastName(firstName.get(), lastName.get());
        } else {
            throw new InvalidArgumentException("Ты чорт, вводи правильные аргументы в query");
        }
    }
}
