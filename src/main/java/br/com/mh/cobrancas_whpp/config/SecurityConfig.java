package br.com.mh.cobrancas_whpp.config;

import br.com.mh.cobrancas_whpp.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UsuarioService usuarioService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .userDetailsService(usuarioService)
                .authorizeHttpRequests(auth -> auth
                        // 🔓 LIBERA FRONTEND
                        .requestMatchers(
                                "/",
                                "/index.html",
                                "/css/**",
                                "/js/**"
                        ).permitAll()

                        // 🔓 libera criação do primeiro admin
                        .requestMatchers(HttpMethod.POST, "/usuarios/primeiro-admin").permitAll()

                        // 🔓 libera página de erro
                        .requestMatchers("/error").permitAll()

                        // 🔒 resto protegido
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}