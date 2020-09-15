package pl.larfeusz.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import pl.larfeusz.security.model.AppUser;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser,Integer> {

    AppUser findByUsername(String username);
}
