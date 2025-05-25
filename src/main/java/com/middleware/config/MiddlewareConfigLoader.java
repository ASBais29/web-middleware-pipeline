package com.middleware.config;

import com.middleware.core.Middleware;
import com.middleware.middleware.Logging;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MiddlewareConfigLoader {

    @Bean
    public List<Middleware> middlewareChain(Logging logging) {
        return List.of(logging);
    }
}
