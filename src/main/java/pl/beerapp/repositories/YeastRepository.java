package pl.beerapp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.beerapp.entities.Yeast;

import java.util.List;

public interface YeastRepository extends CrudRepository<Yeast, Long> {

    List<Yeast> findByName(@Param("name") String name);

}
