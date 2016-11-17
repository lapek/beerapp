package pl.beerapp.controllers;

import org.json.simple.JSONObject;
import org.springframework.boot.json.JsonSimpleJsonParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class NewRecipeController {

    @RequestMapping(value = "/recipe/ebc/", method = RequestMethod.GET, produces = "application/json")
    public String calculateColor(@RequestParam("grain") double grainKg, @RequestParam("color") double color, @RequestParam("volume") double volume, HttpServletResponse response) throws IOException {
        double MCU;
        double WeightOfGrainInLbs = grainKg * 2.2046;
        double ColorOfGrainInDegreesLovibond = color;
        double VolumeInGallons = volume * 0.21997;
        double SRM;
        String colorHex;

        MCU = (WeightOfGrainInLbs) * (ColorOfGrainInDegreesLovibond) / (VolumeInGallons);
        SRM = 1.49 * (MCU * 0.69);
        colorHex = returnColor(SRM);

        JSONObject obj = new JSONObject();
        obj.put("srm", SRM);
        obj.put("color", colorHex);

        return obj.toString();

    }

    public String returnColor(double srm) {
        // by http://www.homebrewtalk.com/showthread.php?t=78018&page=2
        String[] colorArray = {
                "#FFE699",
                "#FFD878",
                "#FFCA5A",
                "#FFBF42",
                "#FBB123",
                "#F8A600",
                "#F39C00",
                "#EA8F00",
                "#E58500",
                "#DE7C00",
                "#D77200",
                "#CF6900",
                "#CB6200",
                "#C35900",
                "#BB5100",
                "#B54C00",
                "#B04500",
                "#A63E00",
                "#A13700",
                "#9B3200",
                "#952D00",
                "#8E2900",
                "#882300",
                "#821E00",
                "#7B1A00",
                "#771900",
                "#701400",
                "#6A0E00",
                "#660D00",
                "#5E0B00",
                "#5A0A02",
                "#600903",
                "#520907",
                "#4C0505",
                "#470606",
                "#440607",
                "#3F0708",
                "#3B0607",
                "#3A070B",
                "#36080A"
        };

        return colorArray[(int) srm - 1];
    }
}
