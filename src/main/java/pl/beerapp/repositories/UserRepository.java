package pl.beerapp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.beerapp.entities.AppUser;

@Repository
public interface UserRepository extends CrudRepository<AppUser, Long> {
}
