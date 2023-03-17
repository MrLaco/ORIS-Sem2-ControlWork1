package ru.kpfu.itis.service.impl;

import lombok.*;
import org.springframework.stereotype.*;
import ru.kpfu.itis.dto.*;
import ru.kpfu.itis.repository.*;
import ru.kpfu.itis.service.*;

@Service
@AllArgsConstructor
public class LibraryServiceImpl implements LibraryService {

    private final LibraryRepository libraryRepository;

    @Override
    public LibraryResponseDto getLibraryByAddress(String address) {

        LibraryResponseDto response = LibraryResponseDto.fromLibrary(
                libraryRepository.getLibraryByAddress(address));

        if (response == null) {
            throw new NullPointerException("Library is null");
        } else {
            return response;
        }
    }

    @Override
    public LibraryResponseDto getLibraryByName(String name) {

        LibraryResponseDto response = LibraryResponseDto.fromLibrary(
                libraryRepository.getLibraryByName(name)
        );

        if (response == null) {
            throw new NullPointerException("Library is null");
        } else {
            return response;
        }
    }
}
