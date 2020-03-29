package com.raketlabs.qr.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import com.raketlabs.config.SecurityConfig;



@Configuration
@EnableWebSecurity
public class QRSecurityConfig extends SecurityConfig  {

    @Override
    protected void configurePermitAll(HttpSecurity http) throws Exception {
        super.configurePermitAll(http);
        
        http
        .authorizeRequests()
        .antMatchers("/c/*", "/r/*")
        .access("hasRole('ROLE_ADMIN')");
        
        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/test").permitAll();
        http.authorizeRequests().antMatchers("/generate/qr/**").permitAll();
        http.authorizeRequests().antMatchers("/barcodes/generate").permitAll();
        http.authorizeRequests().antMatchers("/barcodes/qrgen/qrcode").permitAll();
    }

	
}
