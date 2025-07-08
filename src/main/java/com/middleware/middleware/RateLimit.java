package com.middleware.middleware;

import com.middleware.core.Middleware;
import com.middleware.core.MiddlewareChain;
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
    private StringRedisTemplate redisTemplate;

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
            response.setStatus(429);
            response.getWriter().write("Rate limit exceeded (Redis)");
            return;
        }

        chain.next(request, response);
    }
}
