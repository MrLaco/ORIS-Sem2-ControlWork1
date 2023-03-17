package ru.kpfu.itis.service;

import ru.kpfu.itis.dto.*;

public interface ShelfService {

    ShelfResponseDto findShelfByDestination(String destination);

    ShelfResponseDto findShelfByBookName(String bookName);
}
