package pl.beerapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.beerapp.entities.Style;

import java.util.List;

@Repository
public interface StylesRepository extends JpaRepository<Style, Long> {
    List<Style> findAll();

    Style findStyleByName(String name);
}
