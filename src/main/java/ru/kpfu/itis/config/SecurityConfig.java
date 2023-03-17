package ru.kpfu.itis.config;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.web.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                    .antMatchers("/sign_up").not().fullyAuthenticated()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .antMatchers("/home").hasRole("USER")
                    .antMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .usernameParameter("email")
                    .defaultSuccessUrl("/personal_area")
                    .permitAll()
                .and()
                    .logout()
                    .logoutUrl("/logout")
                    .permitAll()
                    .logoutSuccessUrl("/")
                .and()
                    .exceptionHandling()
                    .accessDeniedPage("/403");
        return httpSecurity.build();
    }
}