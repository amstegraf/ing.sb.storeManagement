package com.ing.sb.storeManagement.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
        basePackages = {"com.ing.sb.storeManagement.repositories"}
)
public class StoreManagementConfig {
}
