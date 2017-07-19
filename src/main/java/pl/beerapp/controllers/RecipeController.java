package pl.beerapp.controllers;

import com.nimbusds.jose.JOSEException;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.beerapp.dto.GrainDTO;
import pl.beerapp.dto.HopsDTO;
import pl.beerapp.dto.RecipeDTO;
import pl.beerapp.dto.RecipeDTOs.FermentationDTO;
import pl.beerapp.dto.RecipeDTOs.MashingDTO;
import pl.beerapp.entities.*;
import pl.beerapp.security.AuthUtils;
import pl.beerapp.services.CalculateService;
import pl.beerapp.services.RecipeService;
import pl.beerapp.services.StyleService;
import pl.beerapp.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/recipes")
public class RecipeController {

    private final CalculateService calculateService;
    private final RecipeService recipeService;
    private final UserService userService;
    private final StyleService styleService;

    @Autowired
    public RecipeController(CalculateService calculateService, RecipeService recipeService, UserService userService, StyleService styleService) {
        this.calculateService = calculateService;
        this.recipeService = recipeService;
        this.userService = userService;
        this.styleService = styleService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Iterable<Recipe> findAllRecipes() {
        return recipeService.findAllRecipes();
    }

    @RequestMapping(value = "/list/public", method = RequestMethod.GET)
    public Iterable<Recipe> findAllPublicRecipes() {
        return recipeService.findAllPublicRecipes();
    }

    @RequestMapping(value = "/list/user", method = RequestMethod.GET)
    public Iterable<Recipe> findAllUserRecipes(@Context HttpServletRequest request) throws ParseException, JOSEException {
        try {
            User user = userService.findById(Long.valueOf(AuthUtils.getSubject(request.getHeader(AuthUtils.AUTH_HEADER_KEY))));
            return recipeService.findAllByUser(user);
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping(value = "/last/public", method = RequestMethod.GET)
    public Recipe findLastPublic() {
        return recipeService.findLastPublicRecipe();
    }

    @RequestMapping(value = "/last/user", method = RequestMethod.GET)
    public Recipe findUserLast(@Context HttpServletRequest request) throws ParseException, JOSEException {
        try {
            User user = userService.findById(Long.valueOf(AuthUtils.getSubject(request.getHeader(AuthUtils.AUTH_HEADER_KEY))));
            return recipeService.findLastUserRecipe(user);
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping(value = "/color", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public String calculateColor(@RequestBody List<GrainDTO> grains,
                                 @RequestParam("batchSize") Double batchSize) {
        Double MCU = 0.0;
        Double SRM;
        String Color;
        JSONObject obj = new JSONObject();

        try {
            for (GrainDTO grain : grains) {
                MCU += calculateService.calculateMCU(grain.getWeight(), grain.getColorEBC(), batchSize);
            }
        } catch (Exception e) {
            return null;
        }

        SRM = calculateService.calculateSRM(MCU);
        Color = calculateService.returnColor(SRM);

        obj.put("SRM", SRM);
        obj.put("color", Color);

        return obj.toString();
    }

    @RequestMapping(value = "/sg", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public String calculateGravity(@RequestBody List<GrainDTO> grains,
                                   @RequestParam("batchSize") Double batchSize,
                                   @RequestParam("efficiency") Double efficiency,
                                   @RequestParam("attenuation") Double attenuation) {
        Double OG = 0.0;
        Double FG;
        JSONObject obj = new JSONObject();

        try {
            for (GrainDTO grain : grains) {
                OG += calculateService.calculateMaltSG(grain.getWeight(), grain.getPotential(), batchSize, efficiency);
            }
            FG = (OG - 1) * (1 - (attenuation / 100));
        } catch (Exception e) {
            return null;
        }

        obj.put("OG", OG);
        obj.put("FG", FG);
        obj.put("ABV", calculateService.calculateABV(OG, FG));

        return obj.toString();
    }

    @RequestMapping(value = "/ibu", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public String calculateIBU(@RequestBody List<HopsDTO> hops,
                               @RequestParam("estBoilSize") Double estBoilSize,
                               @RequestParam("batchSize") Double batchSize,
                               @RequestParam("gravity") Double gravity) {
        Double IBU = 0.0;
        Double boilGravity = calculateService.calculateBoilGravity(gravity, estBoilSize, batchSize);
        JSONObject obj = new JSONObject();

        try {
            for (HopsDTO hop : hops) {
                IBU += calculateService.calculateIBURager(hop.getWeight(), hop.getTime(), hop.getAlpha(), estBoilSize, boilGravity);
            }
        } catch (Exception e) {
            return null;
        }

        obj.put("IBU", IBU);

        return obj.toString();
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<Recipe> saveRecipe(@RequestBody RecipeDTO recipeDTO, @Context HttpServletRequest request) throws ParseException, JOSEException {
        Recipe recipe = new Recipe();
        try {
            User user = userService.findById(Long.valueOf(AuthUtils.getSubject(request.getHeader(AuthUtils.AUTH_HEADER_KEY))));
            //User user = userService.findByUsername(recipeDTO.getAuthor());
            recipe.setUser(user);

            List<Grain> grains = new ArrayList<>();
            for (pl.beerapp.dto.RecipeDTOs.GrainDTO grainDTO : recipeDTO.getGrainList()) {
                grains.add(new Grain(
                                grainDTO.getWeight(),
                                recipe,
                                grainDTO.getMaltID()
                        )
                );
            }

            List<HopStore> hopStores = new ArrayList<>();
            for (pl.beerapp.dto.RecipeDTOs.HopStoreDTO hopStoreDTO : recipeDTO.getHopStoreList()) {
                hopStores.add(new HopStore(
                                hopStoreDTO.getWeight(),
                                hopStoreDTO.getTime(),
                                hopStoreDTO.getHopID(),
                                recipe
                        )
                );
            }

            List<Mashing> mashings = new ArrayList<>();
            for (MashingDTO mashingDTO : recipeDTO.getMashingList()) {
                mashings.add(new Mashing(
                                mashingDTO.getTemperature(),
                                mashingDTO.getTime(),
                                mashingDTO.getAmount(),
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

            recipe.setAuthor(user.getUsername());
            recipe.setName(recipeDTO.getName());
            recipe.setStyle(styleService.findOneByName(recipeDTO.getStyle()));
            recipe.setVisible(recipeDTO.getVisible());
            recipe.setBatchSize(recipeDTO.getBatchSize());
            recipe.setBoilTime(recipeDTO.getEstBoilTime());
            recipe.setBoilSize(recipeDTO.getEstBoilSize());
            recipe.setEfficiency(recipeDTO.getEfficiency());

            if (recipeService.saveRecipe(recipe) != null) {
                return new ResponseEntity<>(HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.GONE);
        }

    }

}
