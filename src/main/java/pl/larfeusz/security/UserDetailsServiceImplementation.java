package pl.larfeusz.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.larfeusz.security.model.AppUser;
import pl.larfeusz.security.repository.AppUserRepository;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {

    AppUserRepository appUserRepository;

    @Autowired
    public UserDetailsServiceImplementation(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return appUserRepository.findByUsername(s);
    }


}
