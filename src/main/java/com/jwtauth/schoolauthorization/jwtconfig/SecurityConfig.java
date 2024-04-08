package com.jwtauth.schoolauthorization.jwtconfig;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true)
public class SecurityConfig {

  @Value("${jwt.secret}")
  private String secret;

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
    return httpSecurity.authorizeHttpRequests(authorize ->             // Here we set authentication for all endpoints
        authorize.
          anyRequest().authenticated())
      // Here we enable that we will accept JWTs
      .oauth2ResourceServer(configure -> configure.jwt(Customizer.withDefaults()))
      .build();
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
