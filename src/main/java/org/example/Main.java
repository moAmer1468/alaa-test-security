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
        
        if (!manager.userExists("admin")) {
            manager.createUser(User.withUsername("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build());
        }
        
        if (!manager.userExists("user")) {
            manager.createUser(User.withUsername("user")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build());
        }
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}