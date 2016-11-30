package pl.beerapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.beerapp.entities.Styles;
import pl.beerapp.repositories.StylesRepository;

import java.util.List;

@RestController
public class StylesController {

    private StylesRepository stylesRepository;

    @Autowired
    public void setStylesRepository(StylesRepository stylesRepository) {
        this.stylesRepository = stylesRepository;
    }

    @RequestMapping(value = "/api/styles/list", method = RequestMethod.GET)
    public Iterable<Styles> findAllStyles() {
        return stylesRepository.findAll();
    }
}
