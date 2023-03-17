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
public class BookInstanceController {

    private final BookInstanceService bookInstanceService;
    private final BookInstanceRepository bookInstanceRepository;

    @Autowired
    public BookInstanceController(BookInstanceServiceImpl bookInstanceService, BookInstanceRepository bookInstanceRepository) {
        this.bookInstanceService = bookInstanceService;
        this.bookInstanceRepository = bookInstanceRepository;
    }

    @GetMapping("/service/bookinstance/create")
    public boolean createBook(
            @RequestParam Optional<String> name,
            @RequestParam Optional<String> author,
            @RequestParam Optional<Integer> publicationYear,
            @RequestParam Optional<String> publisherHouse,
            @RequestParam Optional<Book> book,
            @RequestParam Optional<Shelf> shelf
    ) {
        if (name.isPresent() && author.isPresent() && publicationYear.isPresent() &&
                publisherHouse.isPresent() && book.isPresent() && shelf.isPresent()
        ) {
            BookInstance bookInstance = BookInstance.builder()
                    .name(name.get())
                    .author(author.get())
                    .publicationYear(publicationYear.get())
                    .publisherHouse(publisherHouse.get())
                    .book(book.get())
                    .shelf(shelf.get())
                    .build();
            bookInstanceRepository.save(bookInstance);

            return true;
        } else {
            return false;
        }
    }

    @GetMapping("/service/bookinstance/get")
    public List<BookInstanceResponseDto> getAllBookInstancesByAuthorOrByName(
            @RequestParam Optional<String> author,
            @RequestParam Optional<String> name
    ) throws InvalidArgumentException {

        if (name.isPresent() && author.isPresent()) {
            throw new InvalidArgumentException("Ты чорт, вводи правильные аргументы в query");
        } else if (name.isPresent()) {
            return bookInstanceService.findAllByName(name.get());
        } else if (author.isPresent()) {
            return bookInstanceService.findAllByAuthor(author.get());
        } else {
            throw new InvalidArgumentException("Ты чорт, вводи правильные аргументы в query");
        }
    }
}
