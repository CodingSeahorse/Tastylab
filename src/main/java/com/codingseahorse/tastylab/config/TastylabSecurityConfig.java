package com.codingseahorse.tastylab.config;

import com.codingseahorse.tastylab.jwt.JwtTokenVerifier;
import com.codingseahorse.tastylab.jwt.JwtUsernamePasswordAuthenticationFilter;
import com.codingseahorse.tastylab.service.WelcomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;

import static com.codingseahorse.tastylab.model.member.MembershipRole.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Profile(value = {"development", "production"})
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class TastylabSecurityConfig extends WebSecurityConfigurerAdapter{

    private final PasswordEncoder passwordEncoder;
    private final WelcomeService welcomeService;
    private JwtUsernamePasswordAuthenticationFilter authenticationFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        authenticationFilter =
                new JwtUsernamePasswordAuthenticationFilter(authenticationManagerBean());

        authenticationFilter.setFilterProcessesUrl("/api/welcome/login");

        http
                .cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
                .and()
                .csrf().disable()
                .sessionManagement()
                    .sessionCreationPolicy(STATELESS)
                .and()
                .addFilterBefore(new JwtTokenVerifier(), JwtUsernamePasswordAuthenticationFilter.class)
                .addFilter(authenticationFilter)
                .authorizeRequests()
                .antMatchers( // allow basic files
                       "/*.js",
                        "/*.scss",
                        "/*.css").permitAll()
                .antMatchers( // allow using swagger
                        "/swagger-ui/**",
                        "/swagger-resources/**",
                        "/v2/**").permitAll()
                .antMatchers( // allow basic-calls of tastylab
                        "/api/welcome/**",
                        "/api/recipe/lizzy",
                        "/api/recipe/home").permitAll()
                .antMatchers(// allow member calls
                        "/api/member",
                        "/api/member/**",
                        "/api/recipe",
                        "/api/recipe/**"
                        ).hasRole(BLOGGER.name())
                .antMatchers("/**").hasRole(ADMIN.name()) // allow admin everything
                .anyRequest()
                .authenticated();
    }


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(welcomeService);
        return provider;
    }
}
