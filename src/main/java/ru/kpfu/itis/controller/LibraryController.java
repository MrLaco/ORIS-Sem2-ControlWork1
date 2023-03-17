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
public class LibraryController {

    private final LibraryService libraryService;
    private final LibraryRepository libraryRepository;

    @Autowired
    public LibraryController(LibraryServiceImpl libraryService, LibraryRepository libraryRepository) {
        this.libraryService = libraryService;
        this.libraryRepository = libraryRepository;
    }

    @GetMapping("/service/library/create")
    public boolean createLibrary(
            @RequestParam Optional<String> name,
            @RequestParam Optional<String> address
    ) {
        if (name.isPresent() && address.isPresent()) {
            Library library = Library.builder()
                    .name(name.get())
                    .address(address.get())
                    .build();
            libraryRepository.save(library);

            return true;
        } else {
            return false;
        }
    }

    @GetMapping("/service/library/get")
    public LibraryResponseDto getLibraryByNameOrAddress(@RequestParam Optional<String> name, @RequestParam Optional<String> address) throws InvalidArgumentException {

        if (name.isPresent() && address.isPresent()) {
            throw new InvalidArgumentException("QUERY заточено только под name или address");
        } else if (name.isPresent()) {
            return name.map(libraryService::getLibraryByName).orElse(null);
        } else if (address.isPresent()) {
            return address.map(libraryService::getLibraryByAddress).orElse(null);
        } else {
            throw new InvalidArgumentException("Ты чорт, вводи правильные аргументы в query. Пустыми быть не могут!!");
        }
    }
}
