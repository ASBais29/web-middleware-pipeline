package com.middleware.core;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface Middleware {
    void apply(HttpServletRequest request, HttpServletResponse response, MiddlewareChain chain) throws Exception;
}
