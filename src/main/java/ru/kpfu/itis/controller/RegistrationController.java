package ru.kpfu.itis.controller;

import lombok.*;
import org.springframework.data.repository.query.*;
import org.springframework.security.core.*;
import org.springframework.security.core.context.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.dto.*;
import ru.kpfu.itis.exceptions.*;
import ru.kpfu.itis.security.*;
import ru.kpfu.itis.service.impl.*;

import javax.servlet.http.*;
import javax.validation.*;

@AllArgsConstructor
@Controller
public class RegistrationController {

    private CustomReaderDetailsService readerDetailsService;
    private ReaderServiceImpl readerService;

    @GetMapping("/home")
    public String home(HttpServletRequest httpServletRequest) {
        String currentPrincipalName = httpServletRequest.getUserPrincipal().getName();
        return "home";
    }

    @GetMapping("/sign_up")
    public String sign_up(Model model) {
        model.addAttribute("reader", new ReaderRequestDto());

        return "sign_up";
    }

    @PostMapping("/sign_up")
    public String createReader(
            @ModelAttribute("reader") @Valid ReaderRequestDto reader,
            BindingResult bindingResult, Model model,
            HttpServletResponse response, HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return "sign_up";
        }

        try {
            String url = request.getRequestURL().toString().replace(request.getServletPath(), "");
            readerService.create(reader, url);
            readerDetailsService.saveReader(reader);

            Cookie cookie = new Cookie("email", reader.getEmail());
            response.addCookie(cookie);

        } catch (SuchUserExistsException e) {
            model.addAttribute("readerNameError", "Пользователь с таким именем уже существует!");
        }

        return "redirect:/personal_area";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            request.getSession().invalidate();
        }
        return "redirect:/";
    }

    @GetMapping("/verification")
    public String verify(@Param("code") String code) {
        if (readerService.verify(code)) {
            return "verification_success";
        } else {
            return "verification_failed";
        }
    }
}
