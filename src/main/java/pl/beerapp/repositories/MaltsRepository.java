package pl.beerapp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.beerapp.entities.Malts;

import java.util.List;

public interface MaltsRepository extends CrudRepository<Malts, Long> {

    List<Malts> findByName(@Param("name") String name);

}
