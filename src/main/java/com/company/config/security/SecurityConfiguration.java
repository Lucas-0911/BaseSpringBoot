package com.company.config.security;

import com.company.config.exception.AuthExceptionHandler;
import com.company.model.entity.Account.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private AuthExceptionHandler authExceptionHandler;

    @Autowired
    private JWTAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @SuppressWarnings("removal")
    @Bean
    public SecurityFilterChain configureSecurity(HttpSecurity http) throws Exception {
        http
                .cors(withDefaults())
                .csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((requests) -> requests

                        // Swagger
                        .requestMatchers("/swagger-ui/**", "v3/api-docs/**").anonymous()

                        // check exists
                        .requestMatchers(RegexRequestMatcher.regexMatcher(".+/exists.+")).anonymous()

                        // authen
                        .requestMatchers("/api/v1/auth/password/change").authenticated()
                        .requestMatchers("/api/v1/auth/**").anonymous()

                        // import department
                        .requestMatchers("/api/v1/accounts/info")
                        .hasAnyAuthority(Role.ADMIN.toString())
                        .requestMatchers(HttpMethod.GET, "/api/v1/departments/filter")
                        .hasAnyAuthority(Role.ADMIN.toString())

                        // delete department
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/departments")
                        .hasAnyAuthority(Role.ADMIN.toString())

                        // update department
                        .requestMatchers(HttpMethod.PUT, "/api/v1/departments/**")
                        .hasAnyAuthority(Role.ADMIN.toString())

                        // create department
                        .requestMatchers("/api/v1/accounts/noDepartment")
                        .hasAnyAuthority(Role.ADMIN.toString())
                        .requestMatchers(HttpMethod.POST, "/api/v1/departments")
                        .hasAnyAuthority(Role.ADMIN.toString())

                        // remove accounts in department
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/departments/accounts/**")
                        .hasAnyAuthority(Role.ADMIN.toString())

                        // get department by id
                        .requestMatchers(HttpMethod.GET, "/api/v1/departments/{id}/**")
                        .hasAnyAuthority(Role.ADMIN.toString(), Role.MANAGER.toString())

                        // view list department
                        .requestMatchers(HttpMethod.GET, "/api/v1/departments/**")
                        .hasAnyAuthority(Role.ADMIN.toString())

                        // template excel files
                        .requestMatchers("/api/v1/files/**")
                        .anonymous()

                        .anyRequest().authenticated())

                .httpBasic(withDefaults())
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(authExceptionHandler)
                .accessDeniedHandler(authExceptionHandler);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.applyPermitDefaultValues();
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
