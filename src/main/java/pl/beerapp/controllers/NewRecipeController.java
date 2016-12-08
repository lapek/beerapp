package pl.beerapp.controllers;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.beerapp.dto.GrainDTO;
import pl.beerapp.dto.HopsDTO;
import pl.beerapp.dto.RecipeDTO;
import pl.beerapp.dto.RecipeDTOs.FermentationDTO;
import pl.beerapp.dto.RecipeDTOs.MashingDTO;
import pl.beerapp.entities.*;
import pl.beerapp.repositories.MaltsRepository;
import pl.beerapp.repositories.RecipeRepository;
import pl.beerapp.repositories.UserRepository;
import pl.beerapp.services.CalculateService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class NewRecipeController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private CalculateService service;

    @RequestMapping(value = "/api/recipe/color", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public String calculateColor(@RequestBody List<GrainDTO> grains,
                                 @RequestParam("batchSize") Double batchSize) {
        Double MCU = 0.0;
        Double SRM;
        String Color;
        JSONObject obj = new JSONObject();

        try {
            for (GrainDTO grain : grains) {
                MCU += service.calculateMCU(grain.getWeight(), grain.getColorEBC(), batchSize);
            }
        } catch (Exception e) {
            return null;
        }

        SRM = service.calculateSRM(MCU);
        Color = service.returnColor(SRM);

        obj.put("SRM", SRM);
        obj.put("color", Color);

        return obj.toString();
    }

    @RequestMapping(value = "/api/recipe/sg", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public String calculateGravity(@RequestBody List<GrainDTO> grains,
                                   @RequestParam("batchSize") Double batchSize,
                                   @RequestParam("efficiency") Double efficiency,
                                   @RequestParam("attenuation") Double attenuation) {
        Double OG = 0.0;
        Double FG;
        JSONObject obj = new JSONObject();

        try {
            for (GrainDTO grain : grains) {
                OG += service.calculateMaltSG(grain.getWeight(), grain.getPotential(), batchSize, efficiency);
            }
            FG = (OG - 1) * (1 - (attenuation / 100));
        } catch (Exception e) {
            return null;
        }

        obj.put("OG", OG);
        obj.put("FG", FG);
        obj.put("ABV", service.calculateABV(OG, FG));

        return obj.toString();
    }

    @RequestMapping(value = "/api/recipe/ibu", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public String calculateIBU(@RequestBody List<HopsDTO> hops,
                               @RequestParam("estBoilSize") Double estBoilSize,
                               @RequestParam("batchSize") Double batchSize,
                               @RequestParam("gravity") Double gravity) {
        Double IBU = 0.0;
        Double boilGravity = service.calculateBoilGravity(gravity, estBoilSize, batchSize);
        JSONObject obj = new JSONObject();

        try {
            for (HopsDTO hop : hops) {
                IBU += service.calculateIBURager(hop.getWeight(), hop.getTime(), hop.getAlpha(), estBoilSize, boilGravity);
            }
        } catch (Exception e) {
            return null;
        }

        obj.put("IBU", IBU);

        return obj.toString();
    }

    @RequestMapping(value = "/api/recipe/save", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<Recipe> saveRecipe(@RequestBody RecipeDTO recipeDTO) {
        Recipe recipe = new Recipe();
        try {
            User user = userRepository.findByUsername(recipeDTO.getAuthor());
            recipe.setUser(user);

            List<Grain> grains = new ArrayList<>();
            for(pl.beerapp.dto.RecipeDTOs.GrainDTO grainDTO: recipeDTO.getGrainList()){
                grains.add(new Grain(
                        grainDTO.getWeight(),
                        recipe,
                        grainDTO.getMaltID()
                        )
                );
            }

            List<HopStore> hopStores = new ArrayList<>();
            for(pl.beerapp.dto.RecipeDTOs.HopStoreDTO hopStoreDTO: recipeDTO.getHopStoreList()){
                hopStores.add(new HopStore(
                        hopStoreDTO.getWeight(),
                        hopStoreDTO.getTime(),
                        hopStoreDTO.getHopID(),
                        recipe
                        )
                );
            }

            List<Mashing> mashings = new ArrayList<>();
            for(MashingDTO mashingDTO: recipeDTO.getMashingList()){
                mashings.add(new Mashing(
                        mashingDTO.getTemperature(),
                        mashingDTO.getTime(),
                        mashingDTO. getAmount(),
                        recipe
                        )
                );
            }

            recipe.setGrains(grains);
            recipe.setHopStores(hopStores);
            recipe.setMashings(mashings);

            FermentationDTO fermentationDTO = recipeDTO.getFermentationDTO();
            recipe.setFermentation(new Fermentation(
                    fermentationDTO.getPrimaryTime(),
                    fermentationDTO.getPrimaryTemperature(),
                    fermentationDTO.getSecondaryTime(),
                    fermentationDTO.getSecondaryTemperature(),
                    recipe, recipeDTO.getYeastID()
                    )
            );

            recipe.setName(recipeDTO.getName());
            recipe.setStyle(recipeDTO.getStyle());
            recipe.setVisible(recipeDTO.getVisible());
            recipe.setBatchSize(recipeDTO.getBatchSize());
            recipe.setBoilTime(recipeDTO.getEstBoilTime());
            recipe.setBoilSize(recipeDTO.getEstBoilSize());
            recipe.setEfficiency(recipeDTO.getEfficiency());

            if (recipeRepository.save(recipe) != null) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.GONE);
        }

    }


}
