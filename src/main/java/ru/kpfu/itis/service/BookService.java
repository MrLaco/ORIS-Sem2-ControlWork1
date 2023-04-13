package ru.kpfu.itis.service;

import org.springframework.beans.factory.annotation.*;
import ru.kpfu.itis.dto.*;

public interface BookService {

    BookResponseDto getBookByName(String name);
    BookResponseDto getBookByNameAndAuthor(String name, String author);
}
