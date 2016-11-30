package pl.beerapp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.beerapp.entities.Hops;

import java.util.List;

public interface HopsRepository extends CrudRepository<Hops, Long> {

    List<Hops> findByName(@Param("name") String name);

}
