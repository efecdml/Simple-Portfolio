package com.gungorefe.simpleportfolio.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityFilterConfig {
    private final AuthenticationProvider authProvider;
    private final JwtAuthenticationFilter jwtAuthFilter;
    private static final String[] blacklist = {
            "/api/pages/page/about/component/simple-card/competent/**",
            "/api/pages/page/contact/component/phone/competent/**",
            "/api/pages/page/contact/component/simple-card/competent/**",
            "/api/pages/page/home/component/carousel-section/competent/**",
            "/api/pages/page/home/component/simple-card/competent/**",
            "/api/pages/page/works/component/detailed-card/competent/**",
            "/api/pages/page/about/competent/**",
            "/api/pages/page/contact/competent/**",
            "/api/pages/page/home/competent/**",
            "/api/pages/page/works/competent/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(customizer -> customizer
                        .requestMatchers(blacklist)
                        .authenticated()
                        .anyRequest()
                        .permitAll())
                .sessionManagement(customizer -> customizer
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(
                        jwtAuthFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return httpSecurity.build();
    }
}
