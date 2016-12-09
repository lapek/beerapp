package pl.beerapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.beerapp.entities.Hops;
import pl.beerapp.repositories.HopsRepository;

@RestController
@RequestMapping(value = "/api/hops")
public class HopsController {

    private HopsRepository hopsRepository;

    @Autowired
    public void setMaltsRepository(HopsRepository hopsRepository) {
        this.hopsRepository = hopsRepository;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Iterable<Hops> findAllSHops() {
        return hopsRepository.findAll();
    }
}
