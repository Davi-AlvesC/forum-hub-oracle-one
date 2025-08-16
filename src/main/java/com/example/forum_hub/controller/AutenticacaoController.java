package com.example.forumhub.controller;

import com.example.forumhub.dto.LoginDTO;
import com.example.forumhub.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping
public class AutenticacaoController {

    private final AuthenticationManager authManager;
    private final TokenService tokenService;

    public AutenticacaoController(AuthenticationManager authManager, TokenService tokenService) {
        this.authManager = authManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody @Valid LoginDTO body) {
        var authToken = new UsernamePasswordAuthenticationToken(body.getUsername(), body.getPassword());
        authManager.authenticate(authToken); // lança exceção se inválido
        var token = tokenService.gerarToken(body.getUsername());
        return ResponseEntity.ok(Map.of("type", "Bearer", "token", token));
    }
}
