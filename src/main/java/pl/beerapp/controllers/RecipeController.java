package pl.beerapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.beerapp.entities.Recipe;
import pl.beerapp.repositories.RecipeRepository;

@RestController
public class RecipeController {

    private RecipeRepository recipeRepository;

    @Autowired
    public void setRecipeRepository(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @RequestMapping(value = "/api/recipes/list/public", method = RequestMethod.GET)
    public Iterable <Recipe> findAllPublicRecipes() {
        return recipeRepository.findAll();
    }

    @RequestMapping(value = "/api/recipes/list/", method = RequestMethod.GET)
    public Iterable <Recipe> findAllUserRecipes() {
        return recipeRepository.findAll();
    }

}
