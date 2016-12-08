package pl.beerapp.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.beerapp.entities.Recipe;

import java.util.List;


@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {

    //@Query("Select a from Account a where a.'#nameoffied'=?1")
    //List<Recipe> findAllPublic(@Param("name") String name);

}
