package com.example.forumhub.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration:3600}")
    private long expirationSeconds;

    public String gerarToken(String username) {
        Algorithm alg = Algorithm.HMAC256(secret);
        Instant agora = Instant.now();
        Instant exp = agora.plus(expirationSeconds, ChronoUnit.SECONDS);

        return JWT.create()
                .withIssuer("forumhub")
                .withSubject(username)
                .withIssuedAt(Date.from(agora))
                .withExpiresAt(Date.from(exp))
                .sign(alg);
    }

    public String validarTokenEObterSubject(String token) {
        Algorithm alg = Algorithm.HMAC256(secret);
        return JWT.require(alg)
                .withIssuer("forumhub")
                .build()
                .verify(token)
                .getSubject();
    }
}
