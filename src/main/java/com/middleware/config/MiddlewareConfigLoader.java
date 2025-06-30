package com.middleware.config;

import com.middleware.core.Middleware;
import com.middleware.middleware.JwtAuth;
import com.middleware.middleware.Logging;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import com.middleware.middleware.RateLimit;

@Configuration
public class MiddlewareConfigLoader {

    @Bean
    public List<Middleware> middlewareChain(Logging logging, JwtAuth jwtAuth, RateLimit rateLimit) {
        return List.of(logging, 
        jwtAuth,
        rateLimit
        );
    }
}
