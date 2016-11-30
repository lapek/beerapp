package pl.beerapp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.beerapp.entities.Styles;

import java.util.List;

public interface StylesRepository extends CrudRepository<Styles, Long> {

    List<Styles> findByName(@Param("name") String name);

}
