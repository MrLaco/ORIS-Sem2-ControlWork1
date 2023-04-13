package ru.kpfu.itis;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit.jupiter.*;
import org.springframework.test.web.servlet.*;
import ru.kpfu.itis.dto.*;
import ru.kpfu.itis.model.*;
import ru.kpfu.itis.repository.*;
import ru.kpfu.itis.service.impl.*;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest(BookController.class)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class BookControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BookRepository bookRepository;
    @MockBean
    private BookServiceImpl bookService;


    @Test
    public void shouldCreateBookSuccessfully() throws Exception {
        mockMvc.perform(get("/service/book/create")
                .param("name", "Some book")
                .param("author", "Some author")
                .with(user("user").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    public void shouldNotCreateBookWithoutNameOrAuthor() throws Exception {
        mockMvc.perform(get("/service/book/create")
                .with(user("user").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));

        mockMvc.perform(get("/service/book/create")
                .param("name", "Some book")
                .with(user("user").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));

        mockMvc.perform(get("/service/book/create")
                .param("author", "Some author")
                .with(user("user").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));

        verify(bookRepository, never()).save(any(Book.class));
    }

    @Test
    public void getBookByNameOrByNameAndAuthor_whenValidInput_thenReturns200() throws Exception {
        String url = "/service/book/get";

        String name = "Some name";
        String author = "Some author";
        BookResponseDto bookResponseDto = BookResponseDto.builder().name(name).author(author).build();

        when(bookService.getBookByNameAndAuthor(name, author)).thenReturn(bookResponseDto);
        when(bookService.getBookByName(name)).thenReturn(bookResponseDto);

        mockMvc.perform(get(url)
                        .param("name", name)
                        .param("author", author)
                        .with(user("user").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":null,\"name\":\"Some name\",\"author\":\"Some author\"}"));

        mockMvc.perform(get(url)
                        .param("name", name)
                        .with(user("user").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":null,\"name\":\"Some name\",\"author\":\"Some author\"}"));
    }
}
