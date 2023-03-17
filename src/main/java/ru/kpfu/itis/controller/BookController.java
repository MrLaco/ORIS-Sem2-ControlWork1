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
public class BookController {

    private final BookService bookService;
    private final BookRepository bookRepository;

    @Autowired
    public BookController(BookServiceImpl bookService, BookRepository bookRepository) {
        this.bookService = bookService;
        this.bookRepository = bookRepository;
    }

    @GetMapping("/service/book/create")
    public boolean createBook(
            @RequestParam Optional<String> name,
            @RequestParam Optional<String> author
    ) {
        if (name.isPresent() && author.isPresent()) {
            Book book = Book.builder()
                    .name(name.get())
                    .author(author.get())
                    .build();
            bookRepository.save(book);

            return true;
        } else {
            return false;
        }
    }

    @GetMapping("/service/book/get")
    public BookResponseDto getBookByNameOrByNameAndAuthor(@RequestParam Optional<String> name, @RequestParam Optional<String> author) throws InvalidArgumentException {

        if (name.isPresent() && author.isPresent()) {
            return bookService.getBookByNameAndAuthor(name.get(), author.get());
        } else if (name.isPresent()) {
            return bookService.getBookByName(name.get());
        } else {
            throw new InvalidArgumentException("Ты чорт, вводи правильные аргументы в query");
        }
    }
}
