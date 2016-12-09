package pl.beerapp.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.beerapp.entities.Recipe;

import java.util.List;


@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {

    @Query("SELECT r FROM recipe r WHERE (r.visible = true) AND (r.name != '') AND (r.id_recipe=( SELECT max(re.id_recipe) FROM recipe re ))")
    Recipe findLastPublic();

    @Query("SELECT r FROM recipe r WHERE (r.id_recipe=( SELECT max(re.id_recipe) FROM recipe re WHERE re.author = :author))")
    Recipe findUserLast(@Param("author") String author);

    @Query("SELECT r FROM recipe r WHERE (r.visible = true) AND (r.name != '')")
    List<Recipe> findAllPublic();

    @Query("SELECT r FROM recipe r WHERE r.author = :author ORDER BY r.id_recipe DESC")
    List<Recipe> findAllByAuthor(@Param("author") String author);

}
