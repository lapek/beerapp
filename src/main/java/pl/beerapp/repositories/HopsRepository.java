package pl.beerapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.beerapp.entities.Hop;

import java.util.List;

@Repository
public interface HopsRepository extends JpaRepository<Hop, Long> {
    List<Hop> findAll();
}
