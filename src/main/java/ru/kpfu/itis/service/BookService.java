package ru.kpfu.itis.service;

import ru.kpfu.itis.dto.*;

public interface BookService {

    BookResponseDto getBookByName(String name);
    BookResponseDto getBookByNameAndAuthor(String name, String author);
}
