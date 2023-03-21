//package com.zzh.spring;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
///**
// * @Description:
// * @Author: zzh
// * @Crete 2023/3/17 15:44
// */
//@Configuration
//@EnableWebSecurity
//public class SecurityConfiguration {
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//       return http
//               .authorizeRequests()
//               .requestMatchers("/**").permitAll()
//               .and()
//               .formLogin()
//               .loginPage("/login")
//               .successForwardUrl("/")
//               .failureForwardUrl("/")
//               .and()
//
//               .httpBasic(Customizer.withDefaults())
//               .build();
//    }
//
//    @Bean
//    public UserDetailsService users() {
//        UserDetails user = User.builder()
//                .username("user")
//                .password("123456")
//                .roles("USER")
//                .build();
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("123456")
//                .roles("USER", "ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user, admin);
//    }
//
//
//
//}
