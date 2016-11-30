package pl.beerapp.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.beerapp.entities.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}
