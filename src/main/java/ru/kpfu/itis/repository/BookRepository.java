package ru.kpfu.itis.repository;

import org.springframework.data.jpa.repository.*;
import ru.kpfu.itis.model.*;

public interface BookRepository extends JpaRepository<Book, Integer> {

    Book getBookByName(String name);
    Book getBookByNameAndAuthor(String name, String author);
}
