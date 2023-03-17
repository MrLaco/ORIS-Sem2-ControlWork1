package ru.kpfu.itis.repository;

import org.springframework.data.jpa.repository.*;
import ru.kpfu.itis.model.*;

public interface ShelfRepository extends JpaRepository<Shelf, Integer> {

    Shelf findShelfByDestination(String destination);

    @Query(value = "select * from books where books.name like ?1", nativeQuery = true)
    Shelf findShelfByBookName(String bookName);
}
