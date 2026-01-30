package br.com.invest.api.user_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration  //Security desativado no .yaml
public class SecurityConfig {

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//        http
//                // desabilita CSRF apenas para o H2
//                .csrf(csrf -> csrf
//                        .ignoringRequestMatchers("/h2-console/**")
//                )
//
//                // libera acesso ao H2 Console
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/h2-console/**").permitAll()
//                        .anyRequest().authenticated()
//                )
//
//                // permite iframes (H2 usa iframe)
//                .headers(headers -> headers
//                        .frameOptions(frame -> frame.disable())
//                )
//
//                // auth básica (por enquanto)
//                .httpBasic(Customizer.withDefaults());
//
//        return http.build();
  //  }
}
