package ru.kpfu.itis.model;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity(name = "libraries")
@Data
@Builder
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String address;

    @OneToMany(mappedBy = "library", fetch = FetchType.EAGER)
    private List<Shelf> shelves;
}
