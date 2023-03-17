package ru.kpfu.itis.controller;

import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.dto.*;
import ru.kpfu.itis.service.impl.*;

import javax.servlet.http.*;

@AllArgsConstructor
@Controller
public class PersonalAreaController {

    private ReaderServiceImpl readerService;

    @GetMapping("/personal_area")
    public String personalArea(Model model, HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();

        String readerEmail = "";
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("email")) {
                readerEmail = cookie.getValue();
            }
        }

        ReaderResponseDto reader = readerService.getReaderByEmail(readerEmail);

        model.addAttribute("firstName", reader.getFirstName());
        model.addAttribute("email", reader.getEmail());
        model.addAttribute("role", reader.getRoles());

        return "personal_area";
    }
}
