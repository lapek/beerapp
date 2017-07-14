package pl.beerapp.services;

import pl.beerapp.entities.Recipe;
import pl.beerapp.entities.User;

import java.util.List;

public interface RecipeService {
    List<Recipe> findAllRecipes();

    List<Recipe> findAllByUser(User user);

    List<Recipe> findAllPublicRecipes();

    Recipe findLastPublicRecipe();

    Recipe findLastUserRecipe(User user);

    //void updateRecipe(Recipe recipe);

    Recipe saveRecipe(Recipe recipe);
}
