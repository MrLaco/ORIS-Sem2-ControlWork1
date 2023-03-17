package ru.kpfu.itis.dto;

import lombok.*;

import javax.validation.constraints.*;

@Data
public class ReaderRequestDto {

    @NotBlank(message = "Fist name shouldn't be blank")
    private String firstName;

    @NotBlank(message = "Last name shouldn't be blank")
    private String lastName;

    private String address;

    private String phoneNumber;

    @Email(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$",
            flags = Pattern.Flag.CASE_INSENSITIVE)
    private String email;

    @Size(min = 8, max = 63, message = "Password should contain from 8 to 63 symbols")
    private String password;
}
