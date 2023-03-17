package ru.kpfu.itis.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
import ru.kpfu.itis.model.*;

import java.util.*;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, Integer> {

    Reader getReaderByPhoneNumber(String phoneNumber);
    Optional<Reader> getReaderByEmail(String email);
    Reader getReaderByFirstNameAndLastName(String firstName, String lastName);
}
