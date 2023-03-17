package ru.kpfu.itis.service;

import ru.kpfu.itis.dto.*;
import java.util.*;

public interface BookInstanceService {

    List<BookInstanceResponseDto> findAllByAuthor(String author);
    List<BookInstanceResponseDto> findAllByName(String name);
}
