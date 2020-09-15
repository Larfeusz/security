package pl.larfeusz.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.larfeusz.security.model.AppUser;
import pl.larfeusz.security.repository.AppUserRepository;


@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter { // zabezpiecza endpointy

    UserDetailsServiceImplementation userDetailsServiceImplementation;
    AppUserRepository appUserRepository;

    @Autowired
    public WebSecurityConfig(UserDetailsServiceImplementation userDetailsServiceImplementation, AppUserRepository appUserRepository) {
        this.userDetailsServiceImplementation = userDetailsServiceImplementation;
        this.appUserRepository = appUserRepository;
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
    @EventListener(ApplicationReadyEvent.class)
    public void get(){
        AppUser appUser = new AppUser("Zwykły", passwordEncoder().encode("Zahasłowany"),"USER");
        appUserRepository.save(appUser);
    }
}
