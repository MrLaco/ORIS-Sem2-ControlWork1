package ru.kpfu.itis.security;

import lombok.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.stereotype.*;
import ru.kpfu.itis.dto.*;
import ru.kpfu.itis.exceptions.*;
import ru.kpfu.itis.model.*;
import ru.kpfu.itis.repository.*;

import java.util.*;

@AllArgsConstructor
@Service
public class CustomReaderDetailsService implements UserDetailsService {

    BCryptPasswordEncoder bCryptPasswordEncoder;

    private final ReaderRepository readerRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return readerRepository.getReaderByEmail(username).map(CustomReaderDetails::new).orElseThrow(
                () -> new UsernameNotFoundException(String.format("Reader %s not found", username))
        );
    }

    public ReaderResponseDto saveReader(ReaderRequestDto readerRequestDto) throws SuchUserExistsException {

        Optional<Reader> readerFromDB = readerRepository.getReaderByEmail(readerRequestDto.getEmail());

        if (readerFromDB.isPresent()) {
            throw new SuchUserExistsException("Такой пользователь уже есть в базе!");
        }

        Role role = new Role("ROLE_USER");
        Set<Role> roles = Set.of(role);

        Reader reader = Reader.builder()
                .firstName(readerRequestDto.getFirstName())
                .lastName(readerRequestDto.getLastName())
                .email(readerRequestDto.getEmail())
                .address(readerRequestDto.getAddress())
                .password(bCryptPasswordEncoder.encode(readerRequestDto.getPassword()))
                .roles(roles)
                .phoneNumber(readerRequestDto.getPhoneNumber())
                .build();

        return ReaderResponseDto.fromReader(readerRepository.save(reader));
    }
}
