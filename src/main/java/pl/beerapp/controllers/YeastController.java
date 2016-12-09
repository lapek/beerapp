package pl.beerapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.beerapp.entities.Yeast;
import pl.beerapp.repositories.YeastRepository;

@RestController
@RequestMapping(value = "/api/yeast")
public class YeastController {
    private YeastRepository yeastRepository;

    @Autowired
    public void setMaltsRepository(YeastRepository yeastRepository) {
        this.yeastRepository = yeastRepository;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Iterable<Yeast> findAllStyles() {
        return yeastRepository.findAll();
    }
}
