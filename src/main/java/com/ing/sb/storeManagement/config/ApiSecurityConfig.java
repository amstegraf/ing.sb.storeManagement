package com.ing.sb.storeManagement.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                .sessionCreationPolicy(STATELESS)
                .and()
                .exceptionHandling()
                .and()

                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .logout().disable()
                .headers().frameOptions().disable()
                .and()

                .authorizeRequests()
                .antMatchers("/api/**").authenticated()
                .antMatchers("/h2-console/**", "/swagger-ui*").permitAll();

    }
}
