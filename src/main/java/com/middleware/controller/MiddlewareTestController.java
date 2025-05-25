package com.middleware.controller;

import com.middleware.core.Middleware;
import com.middleware.core.MiddlewareChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MiddlewareTestController {

    private final List<Middleware> middlewares;

    public MiddlewareTestController(List<Middleware> middlewares) {
        this.middlewares = middlewares;
    }

    @GetMapping("/middlewareTest")
    public void middlewareTest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        MiddlewareChain chain = new MiddlewareChain(middlewares);
        chain.next(request, response);
        response.getWriter().write("✅ Middleware pipeline passed. Hello!");
    }
}
