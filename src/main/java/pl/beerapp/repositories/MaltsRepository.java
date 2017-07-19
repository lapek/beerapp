package pl.beerapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.beerapp.entities.Malt;

import java.util.List;

@Repository
public interface MaltsRepository extends JpaRepository<Malt, Long> {
    List<Malt> findAll();
}
