package pl.beerapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.beerapp.entities.Style;
import pl.beerapp.services.StyleService;

@RestController
@RequestMapping(value = "/api/styles")
public class StylesController {

    private StyleService styleService;

    @Autowired
    public void setStylesRepository(StyleService styleService) {
        this.styleService = styleService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Iterable<Style> findAllStyles() {
        return styleService.findAllStyles();
    }

}
