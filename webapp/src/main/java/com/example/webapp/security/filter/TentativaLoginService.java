package com.example.webapp.security.filter;


import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TentativaLoginService {

    private final int MAX_ATTEMPTS = 5;
    private final long BLOCK_TIME = 1 * 60 * 1000; // 10 minutos

    private final Map<String, LoginAttempt> attemptsCache = new ConcurrentHashMap<>();

    public void loginFailed(String key) {
        LoginAttempt attempt = attemptsCache.getOrDefault(key, new LoginAttempt());
        attempt.increment();
        if (attempt.getAttempts() >= MAX_ATTEMPTS) {
            attempt.block(BLOCK_TIME);
        }
        attemptsCache.put(key, attempt);
    }

    public void loginSucceeded(String key) {
        attemptsCache.remove(key);
    }

    public boolean isBlocked(String key) {
        LoginAttempt attempt = attemptsCache.get(key);
        return attempt != null && attempt.isBlocked();
    }

    private static class LoginAttempt {
        private int attempts = 0;
        private long blockEndTime = 0;

        public void increment() {
            attempts++;
        }

        public int getAttempts() {
            return attempts;
        }

        public void block(long durationMillis) {
            blockEndTime = System.currentTimeMillis() + durationMillis;
        }

        public boolean isBlocked() {
            return System.currentTimeMillis() < blockEndTime;
        }
    }
}

