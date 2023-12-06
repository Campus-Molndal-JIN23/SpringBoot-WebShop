package com.ahmad.web3V.service;


import java.time.Instant;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    // Autowired instance of JwtEncoder for JWT encoding
    @Autowired
    private JwtEncoder jwtEncoder;

    // Autowired instance of JwtDecoder for JWT decoding (not currently used in this class)
    @Autowired
    private JwtDecoder jwtDecoder;

    // Method to generate a JWT token based on authentication information
    public String generateJwt(Authentication auth) {

        // Get the current timestamp
        Instant now = Instant.now();

        // Extract authorities from the authentication object and join them into a space-separated string
        String scope = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        // Build JWT claims with issuer, issuedAt, subject, and custom roles claim
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .subject(auth.getName())
                .claim("roles", scope)
                .build();

        // Encode and return the JWT token
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}

