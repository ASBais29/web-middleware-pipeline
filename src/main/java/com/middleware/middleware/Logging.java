package com.middleware.middleware;

import com.middleware.core.Middleware;
import com.middleware.core.MiddlewareChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

@Component
public class Logging implements Middleware {
    @Override
    public void apply(HttpServletRequest request, HttpServletResponse response, MiddlewareChain chain) throws Exception {
        System.out.println("[LOG] Request: " + request.getMethod() + " " + request.getRequestURI());
        chain.next(request, response);
    }
}
