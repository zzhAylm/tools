package com.zzh.boot3.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/1/26 20:52
 */
@Configuration
public class SpringSecurityConfiguration {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) ->
                requests.requestMatchers("/**", "/index").permitAll()
                        .anyRequest().authenticated()
        );
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();
    }


    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("zzh")
                .password("123456")
                .roles("USER")
                .authorities("read")
                .build();
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("ylm")
                .password("123456")
                .roles("ADMIN", "USER")
                .authorities("write","read")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }



}
