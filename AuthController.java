package co.edu.elbosque.procureit.controller;

import co.edu.elbosque.procureit.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authManager;

    public AuthController(AuthenticationManager authManager) {
        this.authManager = authManager;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        UserDetails ud = (UserDetails) auth.getPrincipal();
        String token = JwtUtil.generateToken(ud.getUsername(), Map.of("roles", ud.getAuthorities()), 1000L * 60 * 60 * 8);
        return ResponseEntity.ok(Map.of("token", token));
        }
}
