package com.pps.course.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    @Bean
    public UserDetailsService getDetailsService() {
        return new UserDetailsServiceImpl();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(getDetailsService());
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/user_add").hasAuthority("ADMIN")
                        .anyRequest().authenticated() // Остальные запросы требуют аутентификации
                ).formLogin((form) -> form
                        .loginPage("/login")
                        .failureUrl("/login?error")
                        .defaultSuccessUrl("/main", true)// Разрешаем доступ к странице входа всем
                )
                .logout((logout) -> logout
                        .logoutUrl("/logout") // Указываем URL для выхода из системы
                        .logoutSuccessUrl("/login?logout")
                        .permitAll() // Разрешаем доступ к странице выхода всем
                ).csrf().disable();

        return http.build();
    }

}
