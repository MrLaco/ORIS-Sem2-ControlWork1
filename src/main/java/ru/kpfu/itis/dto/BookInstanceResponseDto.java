package ru.kpfu.itis.dto;

import lombok.*;
import ru.kpfu.itis.model.*;

@Data
@Builder
public class BookInstanceResponseDto {

    private Integer id;

    private String name;

    private String author;

    private String publisherHouse;

    private Integer publicationYear;

    private Book book;

    private Shelf shelf;

    public static BookInstanceResponseDto fromBookInstance(BookInstance bookInstance) {

        return BookInstanceResponseDto.builder()
                .id(bookInstance.getId())
                .name(bookInstance.getName())
                .author(bookInstance.getAuthor())
                .publisherHouse(bookInstance.getPublisherHouse())
                .publicationYear(bookInstance.getPublicationYear())
                .book(bookInstance.getBook())
                .shelf(bookInstance.getShelf())
                .build();
    }
}
