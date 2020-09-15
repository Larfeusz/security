package pl.larfeusz.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter { // zabezpiecza endpointy

    UserDetailsServiceImplementation userDetailsServiceImplementation;

    @Autowired
    public WebSecurityConfig(UserDetailsServiceImplementation userDetailsServiceImplementation) {
        this.userDetailsServiceImplementation = userDetailsServiceImplementation;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser(new User("Piotr", passwordEncoder().encode("Kalafior123"), Collections.singleton(new SimpleGrantedAuthority("user"))));
    auth.userDetailsService(userDetailsServiceImplementation);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/test1")
                .authenticated()
                .and()
                .formLogin()
                .permitAll();
    }
    @Bean
    public PasswordEncoder passwordEncoder (){
        return new BCryptPasswordEncoder();
    }
}
