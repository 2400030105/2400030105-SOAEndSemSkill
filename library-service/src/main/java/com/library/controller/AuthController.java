package com.library.controller;

import com.library.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;
    public AuthController(JwtUtil jwtUtil) { this.jwtUtil = jwtUtil; }

    // username -> {password, role}
    private static final Map<String, String[]> USERS = Map.of(
        "admin",     new String[]{"admin123", "ADMIN"},
        "librarian", new String[]{"lib123",   "LIBRARIAN"},
        "john",      new String[]{"user123",  "USER"}
    );

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> req) {
        String[] u = USERS.get(req.get("username"));
        if (u == null || !u[0].equals(req.get("password"))) {
            return ResponseEntity.status(401).body(Map.of("error", "Invalid credentials"));
        }
        return ResponseEntity.ok(Map.of("token", jwtUtil.generateToken(req.get("username"), u[1])));
    }
}
