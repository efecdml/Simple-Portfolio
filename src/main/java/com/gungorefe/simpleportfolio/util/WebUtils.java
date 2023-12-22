package com.gungorefe.simpleportfolio.util;

import com.gungorefe.simpleportfolio.dto.Image;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class WebUtils {
    @Autowired
    private HttpServletRequest request;

    // TODO: 22/12/2023 Make secure for production.
    public ResponseCookie createCookie(
            String name,
            String value,
            Duration age
    ) {
        ResponseCookie cookie = ResponseCookie
                .from(name, value)
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(age)
                .build();

        return cookie;
    }

    public String getCookieValue(String name) {
        String header = request.getHeader(HttpHeaders.COOKIE);
        String[] cookies;
        String value;

        if (header == null) {
            return null;
        }

        cookies = header.split("; ");
        value = Arrays.stream(cookies)
                .filter(s -> s.startsWith(name + "="))
                .map(s -> s.substring(name.length() + 1))
                .collect(Collectors.joining());

        return value;
    }

    public ResponseCookie deleteCookie(String name) {
        return createCookie(
                name,
                "",
                Duration.ZERO
        );
    }

    public static <T> ResponseEntity<T> getResponseEntityForCachingDto(T body) {
        return ResponseEntity.ok()
                .eTag(String.valueOf(body.hashCode()))
                .cacheControl(CacheControl.maxAge(Duration.ZERO))
                .body(body);
    }

    public static ResponseEntity<byte[]> getResponseEntityForCachingImage(Image image) {
        return ResponseEntity.ok()
                .eTag(image.name())
                .cacheControl(CacheControl.maxAge(Duration.ZERO))
                .contentType(image.mimeType())
                .body(image.bytes());
    }
}
