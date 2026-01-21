package com.cms.cdl.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class ExecutorConfig {
    @Bean(name = "onboardingExecutor")
    public ExecutorService onboardingExecutor() {
        return Executors.newFixedThreadPool(10);
    }
}
