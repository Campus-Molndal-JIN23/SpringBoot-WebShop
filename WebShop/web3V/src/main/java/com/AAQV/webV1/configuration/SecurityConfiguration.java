package com.AAQV.webV1.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import com.AAQV.webV1.Utils.RSAKeyProperties;


@Configuration
public class SecurityConfiguration {

    private final RSAKeyProperties keys;

    // Constructor to inject RSAKeyProperties
    public SecurityConfiguration(RSAKeyProperties keys){
        this.keys = keys;
    }

    // Bean to configure BCryptPasswordEncoder for password encoding
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // Bean to configure AuthenticationManager with DaoAuthenticationProvider
    @Bean
    public AuthenticationManager authManager(UserDetailsService detailsService){
        DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
        daoProvider.setUserDetailsService(detailsService);
        daoProvider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(daoProvider);
    }

    // Bean to configure SecurityFilterChain
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    // Permit access to specific paths without authentication
                    auth.requestMatchers("/auth/**").permitAll();
                    auth.requestMatchers("/film/hämta-films").permitAll();
                    auth.requestMatchers("/film/hämta{id}").permitAll();
                    auth.requestMatchers("/webshop/artiklar/**").permitAll();
                    auth.requestMatchers("/cart/**").permitAll();

                    // Require ADMIN role for paths under /admin/**
                    auth.requestMatchers("/admin/**").hasRole("ADMIN");

                    // Require ADMIN or USER role for paths under /user/**
                    auth.requestMatchers("/user/**").hasAnyRole("ADMIN", "USER");

                    // Uncomment the lines below if you want to restrict paths further
                    // auth.requestMatchers("/user/**").hasRole("USER");
                    // auth.requestMatchers("/todos/post-todos").hasRole("USER");

                    // Require authentication for any other requests
                    auth.anyRequest().authenticated();
                });

        // Configure OAuth2 resource server with JWT authentication
        http.oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwtAuthenticationConverter());

        // Configure session management to be STATELESS
        http.sessionManagement(
                session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        return http.build();
    }

    // Bean to configure JwtDecoder with public key
    @Bean
    public JwtDecoder jwtDecoder(){
        return NimbusJwtDecoder.withPublicKey(keys.getPublicKey()).build();
    }

    // Bean to configure JwtEncoder with public and private keys
    @Bean
    public JwtEncoder jwtEncoder(){
        JWK jwk = new RSAKey.Builder(keys.getPublicKey()).privateKey(keys.getPrivateKey()).build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }

    // Bean to configure JwtAuthenticationConverter with custom settings
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter(){
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("roles");
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
        jwtConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return jwtConverter;
    }
}
