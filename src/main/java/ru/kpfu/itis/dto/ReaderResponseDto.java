package ru.kpfu.itis.dto;

import lombok.*;
import ru.kpfu.itis.model.*;

import java.util.*;

@Data
@Builder
public class ReaderResponseDto {

    private Integer id;

    private String firstName;

    private String lastName;

    private String address;

    private String phoneNumber;

    private String email;

    private String password;

    private Set<Role> roles;

    public static ReaderResponseDto fromReader(Reader reader) {

        return ReaderResponseDto.builder()
                .id(reader.getId())
                .firstName(reader.getFirstName())
                .lastName(reader.getLastName())
                .address(reader.getAddress())
                .email(reader.getEmail())
                .phoneNumber(reader.getPhoneNumber())
                .password(reader.getPassword())
                .build();
    }
}
