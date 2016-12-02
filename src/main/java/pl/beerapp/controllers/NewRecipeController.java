package pl.beerapp.controllers;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.beerapp.dto.GrainDTO;
import pl.beerapp.dto.HopsDTO;
import pl.beerapp.services.CalculateService;

import java.util.List;

@RestController
public class NewRecipeController {

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

}
