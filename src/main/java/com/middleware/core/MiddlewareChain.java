package com.middleware.core;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Iterator;
import java.util.List;

public class MiddlewareChain {
    private final Iterator<Middleware> iterator;

    public MiddlewareChain(List<Middleware> middlewares) {
        this.iterator = middlewares.iterator();
    }

    public void next(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (iterator.hasNext()) {
            Middleware middleware = iterator.next();
            middleware.apply(request, response, this);
        }
    }
}
