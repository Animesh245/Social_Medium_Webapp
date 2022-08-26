package com.animesh245.social_medium.config.security;

import com.animesh245.social_medium.service.implementaion.UsrDetails;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
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
    private final UsrDetails usrDetails;
    private final AuthSuccessHandler authSuccessHandler;

    public SecurityConfig(PasswordEncoder passwordEncoder, UsrDetails usrDetails, AuthSuccessHandler authSuccessHandler)
    {
        this.passwordEncoder = passwordEncoder;
        this.usrDetails = usrDetails;
        this.authSuccessHandler = authSuccessHandler;
    }

    public void configure(AuthenticationManagerBuilder managerBuilder) throws Exception
    {
        managerBuilder.userDetailsService(usrDetails).passwordEncoder(passwordEncoder);
    }

    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception
    {
        httpSecurity.authorizeHttpRequests()
                .antMatchers("/image/**", "/css/**", "/js/**", "/vendor/**").permitAll()
                .antMatchers("/auth/login/**", "/users/new", "/users/").permitAll()
                .antMatchers("/locations/create","/statuses/delete/**", "/users/delete/**").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers("/locations/list").hasAnyRole("USER", "ADMIN")
                .antMatchers("/statuses/create","/statuses/update/**", "/statuses/delete/**", "/users/update/**").hasAnyAuthority("ROLE_USER")
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
