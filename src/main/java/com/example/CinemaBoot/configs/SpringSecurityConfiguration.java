package com.example.CinemaBoot.configs;

//import com.example.CinemaBoot.services.AppUserDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;

//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
//public class SpringSecurityConfiguration {
//
//    @Autowired
//    private AppUserDetailsService userDetailsService;
//
//    @Bean
//    public PasswordEncoder getEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public SecurityFilterChain getFilterChain(HttpSecurity security) throws Exception {
//        return security.authorizeRequests()
//                .mvcMatchers("/admin").hasRole("ADMIN")
//                .mvcMatchers("/").hasAnyRole("ADMIN", "USER")
//                .mvcMatchers("/h2").permitAll()
//                .anyRequest().authenticated()
//                .and().httpBasic()
//                .and().build();
//    }
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(getEncoder());
//    }
//
//}
