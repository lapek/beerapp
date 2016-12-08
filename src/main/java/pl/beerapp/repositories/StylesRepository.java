package pl.beerapp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.beerapp.entities.Styles;

import java.util.List;

@Repository
public interface StylesRepository extends CrudRepository<Styles, Long> {

    //List<Styles> findByName(@Param("name") String name);

}
