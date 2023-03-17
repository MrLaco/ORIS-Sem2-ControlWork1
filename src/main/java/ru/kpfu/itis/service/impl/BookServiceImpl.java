package ru.kpfu.itis.service.impl;

import lombok.*;
import org.springframework.stereotype.*;
import ru.kpfu.itis.dto.*;
import ru.kpfu.itis.repository.*;
import ru.kpfu.itis.service.*;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public BookResponseDto getBookByName(String name) {

        return BookResponseDto.fromBook(
                bookRepository.getBookByName(name)
        );
    }

    @Override
    public BookResponseDto getBookByNameAndAuthor(String name, String author) {

        return BookResponseDto.fromBook(
                bookRepository.getBookByNameAndAuthor(name, author)
        );
    }
}
