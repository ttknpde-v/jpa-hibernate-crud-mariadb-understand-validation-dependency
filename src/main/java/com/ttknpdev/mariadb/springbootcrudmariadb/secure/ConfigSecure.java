package com.ttknpdev.mariadb.springbootcrudmariadb.secure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ConfigSecure {
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        /* method for create roles (in memory) */
        UserDetails mrAAA = User
                .withUsername("mr.aaa")
                .password("{noop}12345")
                .roles("NORMAL")
                .build();
        UserDetails mrBBB = User
                .withUsername("mr.bbb")
                .password("{noop}12345")
                .roles("EMPLOYEE","NORMAL")
                .build();
        return new InMemoryUserDetailsManager(mrAAA,mrBBB);
    }

    @SuppressWarnings("removal")
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET,"/api/reads").hasAnyRole("NORMAL","EMPLOYEE")
                .requestMatchers(HttpMethod.GET,"/api/read").hasAnyRole("NORMAL","EMPLOYEE")
                .requestMatchers(HttpMethod.POST,"/api/create").hasRole("EMPLOYEE")
                .requestMatchers(HttpMethod.PUT,"/api/update/**").hasRole("EMPLOYEE")
                .requestMatchers(HttpMethod.DELETE,"/api/delete/state/**").hasRole("EMPLOYEE")
                .requestMatchers(HttpMethod.DELETE,"/api/delete/**").hasRole("EMPLOYEE")
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin().disable()
                .httpBasic();

        return httpSecurity.build();
    }
}
