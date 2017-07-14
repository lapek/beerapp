package pl.beerapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.beerapp.entities.Recipe;
import pl.beerapp.entities.User;

import java.util.List;


@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findByVisibleTrue();

    List<Recipe> findByUser(User user);

    Recipe findFirstByVisibleTrueOrderByIdDesc();

    Recipe findFirstByUserOrderByIdDesc(User user);
}
