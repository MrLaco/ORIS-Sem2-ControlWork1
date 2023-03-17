package ru.kpfu.itis.service;

import ru.kpfu.itis.dto.*;

public interface LibraryService {

    LibraryResponseDto getLibraryByAddress(String address);
    LibraryResponseDto getLibraryByName(String name);
}
