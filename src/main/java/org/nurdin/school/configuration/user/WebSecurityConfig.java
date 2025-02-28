package org.nurdin.school.configuration.user;

import org.nurdin.school.exceptions.CustomAccessDeniedException;
import org.nurdin.school.security.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {
    private final JwtAuthFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    public WebSecurityConfig(JwtAuthFilter jwtAuthFilter, AuthenticationProvider authenticationProvider) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
            .csrf(securityFilterChain -> securityFilterChain.disable())
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .authorizeHttpRequests(auth -> {
//                System.out.println("configuring");
//                auth.requestMatchers("/api/v1/bidForWork/rejectTheBid").permitAll();//hasAnyAuthority("HEAD_TEACHER" ,"SECRETARY");
//                auth.requestMatchers("/api/v1/bidForWork/acceptBid").permitAll();//hasAnyAuthority("HEAD_TEACHER" ,"SECRETARY");
//                auth.requestMatchers("/api/v1/bidForWork/getAllBids").permitAll();
//                auth.requestMatchers("/api/v1/bidForWork/approve_the_bid_for_work").permitAll();
//                auth.requestMatchers("/api/v1/bidForWork/createBidForWork").permitAll();
//                auth.requestMatchers("/api/v1/bidForStudy/create").permitAll();
//                auth.requestMatchers("/api/v1/bidForStudy/get_all").permitAll();
//                auth.requestMatchers("/api/v1/bidForStudy/approve_the_bid_for_study").permitAll();
//                auth.requestMatchers("/api/v1/bidForStudy/accept_bid_for_study").permitAll();
//                auth.requestMatchers("/api/v1/bidForStudy/reject_bid_for_study").permitAll();
//                auth.requestMatchers("/api/v1/employee/createBidForWork").permitAll();
//                auth.requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll();
//                auth.requestMatchers("/api/v1/user/**").permitAll();
//                auth.requestMatchers("/api/v1/auth/user-roles").permitAll();
//                auth.requestMatchers("/api/v1/news/**").permitAll();
//                auth.requestMatchers("/api/v1/news/").permitAll();
//                auth.requestMatchers("/api/v1/bidForWork/getAllBids").hasAnyAuthority("HEAD_TEACHER", "SECRETARY");
//                auth.requestMatchers("/api/v1/role/get-all-roles").permitAll();
//                auth.requestMatchers("/api/v1/bidForWork/acceptBid/", "/api/v1/bidForWork/rejectTheBid").
//                    hasAnyAuthority("HEAD_TEACHER", "SECRETARY");
//                auth.requestMatchers("/api/v1/employee/createBidForWork").hasAuthority("EMPLOYEE");
//                auth.requestMatchers("/api/v1/auth/login").permitAll();
//                auth.requestMatchers("/api/v1/auth/verify").permitAll();
//                auth.requestMatchers("/api/v1/auth/resend").permitAll();
//                auth.requestMatchers("/api/v1/news/**/image").permitAll();
//                auth.requestMatchers("/api/v1/auth/refresh").permitAll();
//                auth.requestMatchers("/api/v1/minio/download/**").permitAll();
//                auth.requestMatchers("/favicon.ico", "/**/*.png", "/**/*.jpg", "/**/*.jpeg", "/**/*.css", "/**/*.js")
//                    .permitAll();
                auth.anyRequest().permitAll();
            })
            .exceptionHandling(exception -> {
                exception.accessDeniedHandler(new CustomAccessDeniedException());
            })
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
            .authenticationProvider(authenticationProvider)
            .httpBasic(Customizer.withDefaults())
            .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedOrigins(Arrays.asList(
            "http://localhost:3000",
            "http://localhost:8080",
            "http://192.168.210.213:8080",
            "https://391b-46-251-196-6.ngrok-free.app",
            "https://a241-212-112-126-239.ngrok-free.app",
            "https://a241-212-112-126-239.ngrok-free.app/swagger-ui/index.html",
            "https://40d4-212-112-126-234.ngrok-free.app"
        ));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "Accept"));
        configuration.setExposedHeaders(Arrays.asList("Authorization", "Content-Disposition"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
