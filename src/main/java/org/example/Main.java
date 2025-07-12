package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
@SpringBootApplication
public class Main implements CommandLineRunner {

    @Autowired
    private DataSource dataSource;

    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);

        PasswordEncoder encoder = new BCryptPasswordEncoder(); // بدل ما تعمله @Bean

        if (!manager.userExists("admin")) {
            manager.createUser(User.withUsername("admin")
                    .password(encoder.encode("admin"))
                    .roles("ADMIN")
                    .build());
        }

        if (!manager.userExists("user")) {
            manager.createUser(User.withUsername("user")
                    .password(encoder.encode("password"))
                    .roles("USER")
                    .build());
        }
        if (!manager.userExists("mohamed")) {
            manager.createUser(User.withUsername("mohamed")
                    .password(encoder.encode("123"))
                    .roles("USER")
                    .build());
        }
        if (!manager.userExists("alaa")) {
            manager.createUser(User.withUsername("alaa")
                    .password(encoder.encode("alaa123"))
                    .roles("ADMIN")
                    .build());
        }
        if (!manager.userExists("mostafa")) {
            manager.createUser(User.withUsername("mostafa")
                    .password(encoder.encode("alaa123"))
                    .roles("ADMIN")
                    .build());
        }

    }

    /*
    admin token is
    eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsInJvbGVzIjpbeyJhdXRob3JpdHkiOiJST0xFX0FETUlOIn1dLCJpYXQiOjE3NTIzMjE2NjcsImV4cCI6MTc1MjMzOTY2N30.jEzwXWRIgaA4KiS3HtsQjA9FauMm-Lmy-dVgbexgkiAs
    user token is
    eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwicm9sZXMiOlt7ImF1dGhvcml0eSI6IlJPTEVfVVNFUiJ9XSwiaWF0IjoxNzUyMzIwOTA2LCJleHAiOjE3NTIzMzg5MDZ9.0gjAZ-wZdPRTB8A-86tOOqjKmPSEkMAewVaeCy31a_w
    mohamed token is
    eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtb2hhbWVkIiwicm9sZXMiOlt7ImF1dGhvcml0eSI6IlJPTEVfVVNFUiJ9XSwiaWF0IjoxNzUyMzIwOTgyLCJleHAiOjE3NTIzMzg5ODJ9.ZEmMH0-6htbp8S5qJLQ-_lGu6SjVz0J4G5Qsl0lQdTY
   alaa token is
   eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbGFhIiwicm9sZXMiOlt7ImF1dGhvcml0eSI6IlJPTEVfVVNFUiJ9XSwiaWF0IjoxNzUyMzIxMDU3LCJleHAiOjE3NTIzMzkwNTd9.A3Jp9ot4G6b879roNC0-F7rNLNP-8dcwV33gODPmAWU
    mostafa token is
    eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtb3N0YWZhIiwicm9sZXMiOlt7ImF1dGhvcml0eSI6IlJPTEVfQURNSU4ifV0sImlhdCI6MTc1MjMyMjI5NSwiZXhwIjoxNzUyMzQwMjk1fQ.6vN1K8NAOLXZy7eS2y16Tc2v4QManhk4hXkCVHB9Wzo

     */
}