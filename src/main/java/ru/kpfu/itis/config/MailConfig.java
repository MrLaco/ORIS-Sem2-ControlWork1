package ru.kpfu.itis.config;

import lombok.*;
import org.springframework.boot.context.properties.*;
import org.springframework.context.annotation.*;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "mail")
public class MailConfig {

    private String from;

    private String sender;

    private String subject;

    private String content;
}
