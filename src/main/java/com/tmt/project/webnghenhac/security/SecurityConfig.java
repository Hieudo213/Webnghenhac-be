package com.tmt.project.webnghenhac.security;

import com.tmt.project.webnghenhac.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    private final JwtAuthFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .antMatchers("/**/auth/**")
                .permitAll()
//                .antMatchers("/api/v1/public/singles").permitAll()
//                .antMatchers("/api/v1/public/albums").permitAll()
//                .antMatchers("/api/v1/public/artists").permitAll()
//                .antMatchers("/api/v1/public/pictures/file/{id}").permitAll()
//                .antMatchers("/api/v1/public/playlists").permitAll()
//                .antMatchers("/api/v1/public/songs").permitAll()
//                .antMatchers("/api/v1/public/browses").permitAll()
//                .antMatchers("/api/v1/public/categories").permitAll()
//               .antMatchers("/api/v1/public/singles/{id}").permitAll()
//                .antMatchers("/api/v1/public/musics/file/{id}").permitAll()
              .antMatchers("/api/**").permitAll()
                .antMatchers("/api/admin/**").hasAuthority(String.valueOf(Role.ADMIN))
                .antMatchers("/h2-console/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .cors(cors -> cors.disable());

        return http.build();
    }
}
