package pl.beerapp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.beerapp.entities.AppUser;

import java.util.List;

@Repository
public interface AppUserRepository extends CrudRepository<AppUser, Long> {

    List<AppUser> findByUsername(String username);

}
