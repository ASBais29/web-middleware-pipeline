package com.middleware.middleware;


import com.middleware.core.Middleware;
import com.middleware.core.MiddlewareChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

@Component
public class JwtAuth implements Middleware {

    private static final String SECRET = "anshuman"; // Ideally from config

    @Override
    public void apply(HttpServletRequest request, HttpServletResponse response, MiddlewareChain chain) throws Exception {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Missing or invalid Authorization header");
            return;
        }

        String token = authHeader.substring(7);

        try {
            // You'd use a real JWT library like io.jsonwebtoken.Jwts
            if (!validateToken(token)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid token");
                return;
            }

            chain.next(request, response);

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Token validation failed");
        }
    }

    private boolean validateToken(String token) {
        // ⚠️ Fake validation for demo purposes
        return token.equals("valid-token");
    }

    
}
