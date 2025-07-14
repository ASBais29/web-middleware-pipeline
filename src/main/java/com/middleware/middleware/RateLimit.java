package com.middleware.middleware;

import com.middleware.core.Middleware;
import com.middleware.core.MiddlewareChain;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Counter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RateLimit implements Middleware {

    @Autowired
    private final StringRedisTemplate redisTemplate;
    private final Counter rateLimitExceededCounter;

    @Autowired
    public RateLimit(StringRedisTemplate redisTemplate, MeterRegistry meterRegistry) {
        this.redisTemplate = redisTemplate;
        this.rateLimitExceededCounter = meterRegistry.counter("middleware_rate_limit_exceeded");
    }

    private static final long MIN_INTERVAL_SECONDS = 1; // 1 request per second

    @Override
    public void apply(HttpServletRequest request, HttpServletResponse response, MiddlewareChain chain) throws Exception {
        String ip = request.getRemoteAddr();
        String key = "ratelimit:" + ip;

        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        Long count = ops.increment(key);

        if (count == 1) {
            // Set TTL only on first increment
            redisTemplate.expire(key, MIN_INTERVAL_SECONDS, TimeUnit.SECONDS);
        }

        if (count > 1) {
            rateLimitExceededCounter.increment();
            response.setStatus(429);
            response.getWriter().write("Rate limit exceeded (Redis)");
            return;
        }

        chain.next(request, response);
    }
}
