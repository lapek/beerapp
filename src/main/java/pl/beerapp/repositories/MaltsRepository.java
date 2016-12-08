package pl.beerapp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.beerapp.entities.Malts;

import java.util.List;

@Repository
public interface MaltsRepository extends CrudRepository<Malts, Long> {

    //Malts findOne(@Param("id_malt") Long id_malt);

}
