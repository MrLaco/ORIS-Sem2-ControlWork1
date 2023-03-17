package ru.kpfu.itis.model;

import lombok.*;
import javax.persistence.*;

@Entity(name = "books")
@Data
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String author;

    @OneToOne(mappedBy = "book")
    private BookInstance bookInstance;
}
