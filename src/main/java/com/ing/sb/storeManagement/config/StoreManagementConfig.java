package com.ing.sb.storeManagement.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableJpaRepositories(
        basePackages = {"com.ing.sb.storeManagement.repositories"}
)
@RequiredArgsConstructor
public class StoreManagementConfig implements WebMvcConfigurer {
    private final HandlerInterceptor loggingMiddleware;

    @Bean
    public ObjectMapper objectMapper() {
        return Jackson2ObjectMapperBuilder.json()
                .featuresToEnable(MapperFeature.DEFAULT_VIEW_INCLUSION)
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .modules(new JavaTimeModule())
                .build();
    }

    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new MappingJackson2HttpMessageConverter(objectMapper()));
    }

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingMiddleware).addPathPatterns("/api/v1/**");
    }
}
