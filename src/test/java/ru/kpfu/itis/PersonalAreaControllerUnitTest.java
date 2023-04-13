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
import ru.kpfu.itis.service.impl.*;

import javax.servlet.http.*;
import java.util.*;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class PersonalAreaControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ReaderServiceImpl readerService;

    @Test
    public void testPersonalArea() throws Exception {

        ReaderResponseDto reader = ReaderResponseDto
                .builder()
                .firstName("John")
                .email("john@example.com")
                .roles(Set.of(new Role("ADMIN")))
                .build();

        when(readerService.getReaderByEmail(anyString())).thenReturn(reader);

        Cookie cookie = new Cookie("email", "john@example.com");

        mockMvc.perform(get("/personal_area").cookie(cookie)
                .with(user("user").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(view().name("personal_area"))
                .andExpect(model().attribute("firstName", "John"))
                .andExpect(model().attribute("email", "john@example.com"))
                .andExpect(model().attribute("role", Set.of(new Role("ADMIN"))));
    }
}
