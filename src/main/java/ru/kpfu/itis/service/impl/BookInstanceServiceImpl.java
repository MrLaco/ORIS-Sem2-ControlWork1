package ru.kpfu.itis.service.impl;

import lombok.*;
import org.springframework.stereotype.*;
import ru.kpfu.itis.dto.*;
import ru.kpfu.itis.repository.*;
import ru.kpfu.itis.service.*;

import java.util.*;
import java.util.stream.*;

@Service
@AllArgsConstructor
public class BookInstanceServiceImpl implements BookInstanceService {

    private final BookInstanceRepository bookInstanceRepository;


    @Override
    public List<BookInstanceResponseDto> findAllByAuthor(String author) {

        return bookInstanceRepository.findAllByAuthor(author).stream()
                .map(bookInstance -> BookInstanceResponseDto.builder()
                        .id(bookInstance.getId())
                        .name(bookInstance.getName())
                        .author(bookInstance.getAuthor())
                        .publisherHouse(bookInstance.getPublisherHouse())
                        .publicationYear(bookInstance.getPublicationYear())
                        .book(bookInstance.getBook())
                        .shelf(bookInstance.getShelf())
                        .build()

                ).collect(Collectors.toList());
    }

    @Override
    public List<BookInstanceResponseDto> findAllByName(String name) {

        return bookInstanceRepository.findAllByName(name).stream()
                .map(bookInstance -> BookInstanceResponseDto.builder()
                        .id(bookInstance.getId())
                        .name(bookInstance.getName())
                        .author(bookInstance.getAuthor())
                        .publisherHouse(bookInstance.getPublisherHouse())
                        .publicationYear(bookInstance.getPublicationYear())
                        .book(bookInstance.getBook())
                        .shelf(bookInstance.getShelf())
                        .build()

                ).collect(Collectors.toList());
    }
}
