package com.example.gestao.gestao.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Configuration
@EnableJpaRepositories("com.example.gestao.gestao.repository")
@EnableTransactionManagement
@EntityScan("com.example.gestao.gestao.entity")
public class JpaConfig {
    
}
