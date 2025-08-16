package com.example.forumhub.security;

import com.example.forumhub.model.Usuario;
import com.example.forumhub.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Component
public class TokenFilter extends org.springframework.web.filter.OncePerRequestFilter {

    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;

    public TokenFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {

        String header = request.getHeader("Authorization");
        String token = (StringUtils.hasText(header) && header.startsWith("Bearer ")) ? header.substring(7) : null;

        if (token != null) {
            try {
                String username = tokenService.validarTokenEObterSubject(token);
                Usuario u = usuarioRepository.findByUsername(username).orElse(null);
                if (u != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    var principal = User.builder()
                            .username(u.getUsername())
                            .password(u.getPassword())
                            .roles(u.getRole().replace("ROLE_", ""))
                            .build();

                    var auth = new UsernamePasswordAuthenticationToken(
                            principal, null, principal.getAuthorities());
                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            } catch (Exception ignored) {
                // Token inválido: segue sem autenticar (será barrado pela config de segurança)
            }
        }
        chain.doFilter(request, response);
    }
}
