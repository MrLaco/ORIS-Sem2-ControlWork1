package ru.kpfu.itis.dto;

import lombok.*;
import ru.kpfu.itis.model.*;

@Data
@Builder
public class ShelfResponseDto {

    private Integer id;

    private String name;

    private String destination;

    private Library library;

    public static ShelfResponseDto fromShelf(Shelf shelf) {

        return ShelfResponseDto.builder()
                .id(shelf.getId())
                .name(shelf.getName())
                .destination(shelf.getDestination())
                .library(shelf.getLibrary())
                .build();
    }
}
