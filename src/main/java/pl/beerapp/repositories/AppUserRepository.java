package pl.beerapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import pl.beerapp.entities.AppUser;

public interface AppUserRepository extends CrudRepository<AppUser, Long> {
    AppUser findOneByLogin(String login);
    AppUser findOneByEmail(String email);
}
