package pl.beerapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.beerapp.entities.Yeast;

import java.util.List;

@Repository
public interface YeastRepository extends JpaRepository<Yeast, Long> {
    List<Yeast> findAll();
}
