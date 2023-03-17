package ru.kpfu.itis.dto;

import lombok.*;
import ru.kpfu.itis.model.*;

@Data
@Builder
public class BookResponseDto {

    private Integer id;

    private String name;

    private String author;

    public static BookResponseDto fromBook(Book book) {

        return BookResponseDto.builder()
                .id(book.getId())
                .name(book.getName())
                .author(book.getAuthor())
                .build();
    }
}
