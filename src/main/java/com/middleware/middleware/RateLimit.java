package com.middleware.middleware;

import org.springframework.stereotype.Component;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.middleware.core.Middleware;
import com.middleware.core.MiddlewareChain;

@Component
public class RateLimit implements Middleware {

    private final Map<String, Long> lastRequestTime = new ConcurrentHashMap<>();
    private static final long MIN_INTERVAL_MS = 1000; // 1 request per second per IP

    @Override
    public void apply(HttpServletRequest request, HttpServletResponse response, MiddlewareChain chain)
            throws Exception {
        String ip = request.getRemoteAddr();
        long now = System.currentTimeMillis();

        Long lastTime = lastRequestTime.get(ip);
        if (lastTime != null && (now - lastTime) < MIN_INTERVAL_MS) {
            response.setStatus(429);
            response.getWriter().write("Rate limit exceeded");
            return;
        }

        lastRequestTime.put(ip, now);
        chain.next(request, response);
    }
}