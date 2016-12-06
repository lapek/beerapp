package pl.beerapp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.beerapp.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    public User findByUsername(String username);
}
