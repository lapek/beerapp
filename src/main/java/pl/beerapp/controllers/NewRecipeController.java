package pl.beerapp.controllers;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.beerapp.dto.GrainColorDTO;
import pl.beerapp.dto.GrainGravityDTO;
import pl.beerapp.services.CalculateService;

import java.util.List;

@RestController
public class NewRecipeController {

    @Autowired
    private CalculateService service;

    @RequestMapping(value = "/api/recipe/color", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public String calculateColor(@RequestBody List<GrainColorDTO> grains) {
        Double MCU = 0.0;
        Double SRM = 0.0;
        String Color = "";
        JSONObject obj = new JSONObject();
        try {
            for (GrainColorDTO grain : grains) {
                MCU += service.calculateMCU(grain.getGrain(), grain.getColorEBC(), grain.getBatchSize());
            }
        } catch (Exception e) {
            return null;
        }
        SRM = service.calculateSRM(MCU);
        Color = service.returnColor(SRM);

        obj.put("SRM", SRM);
        obj.put("Color", Color);

        return obj.toString();
    }

    @RequestMapping(value = "/api/recipe/sg", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public String calculateGravity(@RequestBody List<GrainGravityDTO> grains) {
        Double OG = 0.0;
        Double FG = 0.0;
        Double attenuation = 75.0;
        JSONObject obj = new JSONObject();

        try {
            for (GrainGravityDTO grain : grains) {
                OG += service.calculateMaltSG(grain.getWeight(), grain.getPotential(), grain.getBatchSize(), grain.getEfficiency());
                attenuation = grain.getAttenuation();
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

}
