package pl.beerapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.beerapp.entities.Recipe;
import pl.beerapp.entities.User;

import java.util.List;


@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    //@Query("SELECT r FROM recipe r WHERE r.visible = true ORDER BY r.id_recipe DESC")
    //List<Recipe> findAllPublic();

    List<Recipe> findByVisibleTrue();

    //@Query("SELECT r FROM recipe r WHERE r.id_user = :id_user ORDER BY r.id_recipe DESC")
    List<Recipe> findByUser(User user);

}
