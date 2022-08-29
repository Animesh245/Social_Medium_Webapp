package com.animesh245.social_medium.config.security;

import com.animesh245.social_medium.service.definition.IUserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = {"com.animesh245.social_medium.config"})
public class SecurityConfig
{
    private final PasswordEncoder passwordEncoder;
    private final IUserService iUserService;
    private final AuthSuccessHandler authSuccessHandler;

    public SecurityConfig(PasswordEncoder passwordEncoder, IUserService iUserService, AuthSuccessHandler authSuccessHandler)
    {
        this.passwordEncoder = passwordEncoder;
        this.iUserService = iUserService;
        this.authSuccessHandler = authSuccessHandler;
    }

    @Autowired
    public void configure(@NotNull AuthenticationManagerBuilder managerBuilder) throws Exception
    {
        managerBuilder.userDetailsService(iUserService).passwordEncoder(passwordEncoder);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception
    {

        httpSecurity.csrf().disable()
                .authorizeHttpRequests()
                    .antMatchers("/images/**", "/css/*", "/js/*", "/vendor/*").permitAll()
                    .antMatchers("/auth/**", "/users/create","/users/").permitAll()
                    .antMatchers("/locations/").hasAnyRole("USER", "ADMIN")
                    .antMatchers("/locations/**","/statuses/delete/**", "/users/delete/**").hasRole("ADMIN")
                    .antMatchers(HttpMethod.POST,"/statuses/","/users/").hasRole("USER")
                    .antMatchers("/statuses/**","/users/update/**").hasRole("USER")
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                        .loginPage("/auth/login")
                        .loginProcessingUrl("/login-processing")
                        .permitAll()
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .successHandler(authSuccessHandler)
                        .failureUrl("/auth/login?error=true")
                .and()
                        .logout()
                        .logoutUrl("/auth/logout")
                        .logoutSuccessUrl("/auth/login").permitAll()
                .and()
                        .exceptionHandling().accessDeniedPage("/auth/403");

        return httpSecurity.build();
    }
}
