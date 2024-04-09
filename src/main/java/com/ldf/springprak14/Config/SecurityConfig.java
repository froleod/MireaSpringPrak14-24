package com.ldf.springprak14.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .rememberMe(r -> r.tokenRepository(jdbcTokenRepository()).userDetailsService(userDetailsService(passwordEncoder())).key("uniqueAndSecret"))
//
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(authorize ->
//                        authorize
//                                .requestMatchers("/login").permitAll()
//                                .anyRequest().authenticated()
//                )
//                .sessionManagement(session ->
//                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                );
//        return http.build();
//    }



    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/MireaCourse");
        dataSource.setUsername("postgres");
        dataSource.setPassword("aks");
        return dataSource;
    }
    @Bean
    public JdbcDaoImpl jdbcDao(DataSource dataSource) {
        JdbcDaoImpl jdbcDao = new JdbcDaoImpl();
        jdbcDao.setDataSource(dataSource);
        return jdbcDao;
    }
    @Bean
    public JdbcTokenRepositoryImpl jdbcTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource());
        return tokenRepository;
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder){
        UserDetails admin = User.builder().username("admin").password(encoder.encode("admin")).roles("ADMIN").build();
        UserDetails user = User.builder().username("user").password(encoder.encode("user")).roles("USER").build();
        return new InMemoryUserDetailsManager(admin, user);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService(passwordEncoder()));
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
}
