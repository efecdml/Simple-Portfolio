package com.gungorefe.simpleportfolio.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@EnableCaching
@Configuration
public class AppConfig {
}
