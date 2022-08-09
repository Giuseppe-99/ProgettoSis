package com.example.progettosis.configurations;

import com.example.progettosis.support.authentication.JwtAuthenticationConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/").permitAll()
                .antMatchers("/prodotti").permitAll()
                .antMatchers("/ordini/id").permitAll()
                .antMatchers("/carrello/ricerca").permitAll()
                .antMatchers("/prodotti/nome").permitAll()
                .antMatchers("/carrello/inserimento").permitAll()
                .antMatchers("/ordini/crea").permitAll()
                .antMatchers("/carrello/elimina").permitAll()
                .antMatchers("/prodotti/cerca").permitAll()
                .antMatchers("/ordini/f").permitAll()
                .antMatchers("/carrello/ric").permitAll()
                .antMatchers("/carrello/ins").permitAll()
                .antMatchers("/carrello/elim").permitAll()
                .antMatchers("/carrello/tot").permitAll()
                .antMatchers("/carrello/aum").permitAll()
                .antMatchers("/carrello/dim").permitAll()
                .antMatchers("/ordini/cr").permitAll()
                .antMatchers("/prodotti/all").permitAll()
                .anyRequest().authenticated().and().oauth2ResourceServer().jwt().jwtAuthenticationConverter(authenticationConverter());
    }

    @Bean
    public Converter<Jwt, AbstractAuthenticationToken> authenticationConverter() {
        return new JwtAuthenticationConverter();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("OPTIONS");
        configuration.addAllowedMethod("GET");
        configuration.addAllowedMethod("POST");
        configuration.addAllowedMethod("PUT");
        source.registerCorsConfiguration("/**", configuration);
        return new CorsFilter(source);
    }


}