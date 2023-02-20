package com.pragma.userservice.infrastructure.configuration;

import com.pragma.userservice.infrastructure.configuration.jwt.JwtAuthenticationFilter;
import com.pragma.userservice.infrastructure.configuration.jwt.RoleCreatorFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Security configuration class. For the moment doesn't do anything.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final RoleCreatorFilter roleCreatorFilter;
    private final GlobalWrapFilter globalWrapFilter;

    public SecurityConfiguration(JwtAuthenticationFilter jwtAuthFilter, AuthenticationProvider authenticationProvider,
                                 RoleCreatorFilter roleCreatorFilter, GlobalWrapFilter globalWrapFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.authenticationProvider = authenticationProvider;
        this.roleCreatorFilter = roleCreatorFilter;
        this.globalWrapFilter = globalWrapFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests()
                .antMatchers("/auth/**")
                    .permitAll()

                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(globalWrapFilter, JwtAuthenticationFilter.class);
        http
                .antMatcher("/save")
                .addFilterAfter(roleCreatorFilter, JwtAuthenticationFilter.class);
        return http.build();
    }

}
