package org.example.controller;

import io.jsonwebtoken.Claims;
import org.example.config.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TokenController {


    @Autowired
    private JwtUtil jwtUtil;

    //This is used from the postman to put the username of the current user
    @PostMapping("/token")
    public String getToken(@RequestParam String username) {
        return jwtUtil.generateToken(username);
    }

    //this method must print me the username for this person
    @GetMapping("/secure")
    public String secureEndpoint(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        Claims claims = jwtUtil.extractClaims(token);
        System.out.println(claims);
        return "👋 أهلاً " + claims.getSubject() + " (role: " + claims.get("role") + ")";
    }

}
