package pl.beerapp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.beerapp.entities.Yeast;

import java.util.List;

@Repository
public interface YeastRepository extends CrudRepository<Yeast, Long> {

    //List<Yeast> findByName(@Param("name") String name);

}
