package pl.beerapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.beerapp.entities.Yeast;
import pl.beerapp.services.YeastService;

@RestController
@RequestMapping(value = "/api/yeast")
public class YeastController {

    private final YeastService yeastService;

    @Autowired
    public YeastController(YeastService yeastService) {
        this.yeastService = yeastService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Iterable<Yeast> findAllStyles() {
        return yeastService.findAllYeasts();
    }
}
