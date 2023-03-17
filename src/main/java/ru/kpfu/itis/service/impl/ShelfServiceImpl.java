package ru.kpfu.itis.service.impl;

import lombok.*;
import org.springframework.stereotype.*;
import ru.kpfu.itis.dto.*;
import ru.kpfu.itis.repository.*;
import ru.kpfu.itis.service.*;

@Service
@AllArgsConstructor
public class ShelfServiceImpl implements ShelfService {

    private final ShelfRepository shelfRepository;

    @Override
    public ShelfResponseDto findShelfByDestination(String destination) {

        return ShelfResponseDto.fromShelf(
                shelfRepository.findShelfByDestination(destination)
        );
    }

    @Override
    public ShelfResponseDto findShelfByBookName(String bookName) {

        return ShelfResponseDto.fromShelf(
                shelfRepository.findShelfByBookName(bookName)
        );
    }
}
