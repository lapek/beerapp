package pl.beerapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.beerapp.entities.Recipe;
import pl.beerapp.entities.User;
import pl.beerapp.repositories.RecipeRepository;

import java.util.List;

@Service("recipeService")
@Transactional
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> findAllRecipes(){
        return recipeRepository.findAll();
    }

    public List<Recipe> findAllByUser(User user){
        return recipeRepository.findByUser(user);
    }

    public List<Recipe> findAllPublicRecipes(){
        //return recipeRepository.findAllPublic();
        return recipeRepository.findByVisibleTrue();
    }

//    public Recipe findLastPublicRecipe(){
//
//    }
//
//    public Recipe findLastUserRecipe(Long id){
//
//    }
}
