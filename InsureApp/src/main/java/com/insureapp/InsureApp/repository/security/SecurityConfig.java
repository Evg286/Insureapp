package com.insureapp.InsureApp.repository.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().ignoringRequestMatchers("/saveMsg/")
                .and().anonymous().and()
                .authorizeHttpRequests()
                .requestMatchers("/assets/**").permitAll()
                .requestMatchers("/home").permitAll()
                .requestMatchers("/login").permitAll()
                .requestMatchers("/logout").authenticated()
                .requestMatchers("/contact").permitAll()
                .requestMatchers("/plans").permitAll()
                .requestMatchers("/dashboard").authenticated()
                .requestMatchers("/saveMsg").permitAll()
                .requestMatchers("/register").permitAll()
                .requestMatchers("/addUser").permitAll()
                .requestMatchers("/inquiries").hasRole("ADMIN")
                .requestMatchers("/displayInquiries").hasRole("ADMIN")
                .requestMatchers("/closeInquiry").hasRole("ADMIN")
                .requestMatchers("/userProfile").hasRole("ADMIN")
                .requestMatchers("/displayProfile").authenticated()
                .requestMatchers("/updateProfile").authenticated()
                .requestMatchers("/api/**").authenticated()
                .requestMatchers("/application").authenticated()
                .requestMatchers("/applications").hasRole("ADMIN")
                .requestMatchers("/displayApplications").hasRole("ADMIN")
                .requestMatchers("/saveApplication").authenticated()
                .requestMatchers("/approveApplication").hasRole("ADMIN")

                .and().formLogin().loginPage("/login").defaultSuccessUrl("/dashboard").failureUrl("/login?error=true").permitAll()
                .and().logout().logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true).permitAll()
                .and().httpBasic();

        return http.build();
    }
}

