package com.gungorefe.simpleportfolio.service.security;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.net.HttpHeaders;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.concurrent.ExecutionException;

@Service
public class LoginAttemptService {
    private static final int MAX_ATTEMPT = 4;
    private final LoadingCache<String, Integer> cache;
    @Autowired
    private HttpServletRequest request;

    public LoginAttemptService() {
        super();
        cache = CacheBuilder.newBuilder()
                .expireAfterWrite(Duration.ofDays(1))
                .build(new CacheLoader<String, Integer>() {
                    @Override
                    public Integer load(String key) throws Exception {
                        return 0;
                    }
                });
    }

    public boolean isBlocked() {
        return getAttempts(getIp()) >= MAX_ATTEMPT;
    }

    public void failedLogin() {
        String ip = getIp();
        int currentAttempts = getAttempts(ip);
        int newAttempts = currentAttempts + 1;

        cache.put(ip, newAttempts);
    }

    private int getAttempts(String ip) {
        int attempts;

        try {
            attempts = cache.get(ip);
        } catch (ExecutionException e) {
            attempts = 0;
        }

        return attempts;
    }

    private String getIp() {
        String xfHeader = request.getHeader(HttpHeaders.X_FORWARDED_FOR);
        String ip = xfHeader == null ||
                xfHeader.isEmpty() ||
                !xfHeader.contains(request.getRemoteAddr())
                ? request.getRemoteAddr()
                : xfHeader.split(",")[0];

        return ip;
    }
}
