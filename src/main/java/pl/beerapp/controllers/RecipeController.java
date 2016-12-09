package pl.beerapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.beerapp.entities.Recipe;
import pl.beerapp.repositories.RecipeRepository;

@RestController
@RequestMapping(value = "/api/recipes")
public class RecipeController {

    private RecipeRepository recipeRepository;

    @Autowired
    public void setRecipeRepository(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @RequestMapping(value = "/list/public", method = RequestMethod.GET)
    public Iterable<Recipe> findAllPublicRecipes() {
        return recipeRepository.findAllPublic();
    }

    @RequestMapping(value = "/last/public", method = RequestMethod.GET)
    public Recipe findLastPublic() {
        return recipeRepository.findLastPublic();
    }

    @RequestMapping(value = "/last/user", method = RequestMethod.GET)
    public Recipe findUserLast(@RequestParam String author) {
        return recipeRepository.findUserLast(author);
    }

    @RequestMapping(value = "/list/all", method = RequestMethod.GET)
    public Iterable<Recipe> findAll() {
        return recipeRepository.findAll();
    }

    @RequestMapping(value = "/list/user", method = RequestMethod.GET)
    public Iterable<Recipe> findAllUser(@RequestParam String author) {
        return recipeRepository.findAllByAuthor(author);
    }

}
