package ru.kpfu.itis.service.impl;

import lombok.*;
import net.bytebuddy.utility.*;
import org.springframework.mail.javamail.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.config.*;
import ru.kpfu.itis.dto.*;
import ru.kpfu.itis.model.Reader;
import ru.kpfu.itis.repository.*;
import ru.kpfu.itis.service.*;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.*;
import java.util.*;

@Service
@AllArgsConstructor
public class ReaderServiceImpl implements ReaderService {

    private final ReaderRepository readerRepository;
    private final BCryptPasswordEncoder encoder;
    private final JavaMailSender javaMailSender;
    private final MailConfig mailConfig;

    @Override
    public ReaderResponseDto getReaderByEmail(String email) {

        Optional<Reader> reader = readerRepository.getReaderByEmail(email);

        if (reader.isPresent()) {
            return ReaderResponseDto.fromReader(
                    reader.get()
            );
        } else {
            throw new RuntimeException("Такого ридера не существует!");
        }
    }

    @Override
    public ReaderResponseDto getReaderByPhoneNumber(String phoneNumber) {

        return ReaderResponseDto.fromReader(
                readerRepository.getReaderByPhoneNumber(phoneNumber)
        );
    }

    @Override
    public ReaderResponseDto getReaderByFirstNameAndLastName(String firstName, String lastName) {

        return ReaderResponseDto.fromReader(
                readerRepository.getReaderByFirstNameAndLastName(firstName, lastName)
        );
    }

    @Override
    public ReaderResponseDto create(ReaderRequestDto userDto, String url) {

        String code = RandomString.make(64);
        String encodedPassword = encoder.encode(userDto.getPassword());
        Reader reader = Reader.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .address(userDto.getAddress())
                .phoneNumber(userDto.getPhoneNumber())
                .email(userDto.getEmail())
                .verificationCode(code)
                .password(encodedPassword)
                .build();
        sendVerificationMail(
                userDto.getEmail(),
                userDto.getFirstName() + "" + userDto.getLastName(),
                code, url
        );
        return ReaderResponseDto.fromReader(reader);
    }

    @Override
    public boolean verify(String verificationCode) {
        Reader reader = readerRepository.findByVerificationCode(verificationCode);
        if (reader != null) {
            reader.setVerificationCode(null);
            reader.setEnabled(true);
            readerRepository.save(reader);
            return true;
        }
        return false;
    }

    @Override
    public void sendVerificationMail(String mail, String name, String code, String url) {
        String from = mailConfig.getFrom();
        String sender = mailConfig.getSender();
        String subject = mailConfig.getSubject();
        String content = mailConfig.getContent();


        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        try {
            helper.setFrom(from, sender);

            helper.setTo(mail);
            helper.setSubject(subject);

            content = content.replace("{name}", name);
            content = content.replace("{url}", url + "/verification?code=" + code);

            helper.setText(content, true);
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        javaMailSender.send(mimeMessage);
    }
}
