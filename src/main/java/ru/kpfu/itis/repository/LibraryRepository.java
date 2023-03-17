package ru.kpfu.itis.repository;

import org.springframework.data.jpa.repository.*;
import ru.kpfu.itis.model.*;

public interface LibraryRepository extends JpaRepository<Library, Integer> {

    Library getLibraryByAddress(String address);
    Library getLibraryByName(String name);
}
