package com.gungorefe.simpleportfolio.config.security;

import com.gungorefe.simpleportfolio.service.security.JwtService;
import com.gungorefe.simpleportfolio.util.WebUtils;
import com.gungorefe.simpleportfolio.vo.JwtToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

@RequiredArgsConstructor
@Configuration
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final WebUtils webUtils;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String accessToken = webUtils.getCookieValue(JwtToken.ACCESS_TOKEN.value);
        final String username;

        if (accessToken == null
                || accessToken.isBlank()) {
            filterChain.doFilter(
                    request,
                    response
            );

            return;
        }

        username = jwtService.extractUsername(accessToken);

        if (username != null &&
                SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            Collection<? extends GrantedAuthority> authorities;
            boolean tokenValid = jwtService.isTokenValid(
                    accessToken,
                    userDetails
            );

            if (tokenValid) {
                authorities = userDetails.getAuthorities();

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        authorities
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(
                request,
                response
        );
    }
}
