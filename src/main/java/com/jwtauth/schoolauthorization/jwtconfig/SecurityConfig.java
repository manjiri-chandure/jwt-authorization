package com.jwtauth.schoolauthorization.jwtconfig;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true)
public class SecurityConfig {

  @Value("${jwt.secret}")
  private String secret;
  
  @Autowired
  private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
  private SecretKey stringToSecretKey(String secretKey) {
    return new OctetSequenceKey.Builder(secretKey.getBytes())
      .algorithm(JWSAlgorithm.HS512)
      .build()
      .toSecretKey();
  }
  @Bean
  public JwtDecoder jwtDecoder() {

    byte[] decodedKey = secret.getBytes();
    SecretKey secretKey = new SecretKeySpec(decodedKey, "HMacSHA512");
//    SecretKey key = new SecretKeySpec(keyBytes, 0, keyBytes.length, "HmacSHA512")
    return NimbusJwtDecoder.withSecretKey(secretKey).macAlgorithm(MacAlgorithm.HS512).build();
  }
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
     httpSecurity.authorizeHttpRequests(authorize ->             // Here we set authentication for all endpoints
        authorize.requestMatchers("/v3/api-docs/**").permitAll().
                requestMatchers("/swagger-ui/**").permitAll().
          anyRequest().authenticated()).oauth2ResourceServer(configure -> configure.jwt(Customizer.withDefaults()).authenticationEntryPoint(jwtAuthenticationEntryPoint)

    );
     return httpSecurity.build();
  }

  @Bean
  public AuthenticationEntryPoint authenticationEntryPoint() {
    return (request, response, authException) -> {
      response.setStatus(HttpStatus.UNAUTHORIZED.value());
      response.getWriter().write("Unauthorized");
    };
  }


  @Bean
  public JwtAuthenticationConverter jwtAuthenticationConverter() {
    final JwtGrantedAuthoritiesConverter gac = new JwtGrantedAuthoritiesConverter();
    gac.setAuthoritiesClaimName("Role");
    gac.setAuthorityPrefix("ROLE_");
    final JwtAuthenticationConverter jac = new JwtAuthenticationConverter();
    jac.setJwtGrantedAuthoritiesConverter(gac);
    return jac;
  }

}
