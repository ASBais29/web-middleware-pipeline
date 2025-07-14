package com.middleware.middleware;


import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import com.middleware.config.JwtConfig;
import com.middleware.core.Middleware;
import com.middleware.core.MiddlewareChain;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuth implements Middleware {
    private final JwtConfig jwtConfig;

    @Autowired
    public JwtAuth(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }
    @Override
    public void apply(HttpServletRequest request,
                                    HttpServletResponse response,
                                    MiddlewareChain chain)
            throws Exception {

        String authHeader = request.getHeader("Authorization");
        String secretKey = jwtConfig.getSecret();

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7);
            try {
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                        .build()
                        .parseClaimsJws(jwt)
                        .getBody();

                // Optional: set user context in request
                request.setAttribute("claims", claims);
            } catch (JwtException e) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.getWriter().write("Invalid JWT Token");
                return;

            }
        } else {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("Missing Authorization header");
            return;
        }

        chain.next(request, response);
    }
}