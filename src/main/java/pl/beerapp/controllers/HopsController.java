package pl.beerapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.beerapp.entities.Hop;
import pl.beerapp.services.HopService;

@RestController
@RequestMapping(value = "/api/hops")
public class HopsController {

    private final HopService hopService;

    @Autowired
    public HopsController(HopService hopService) {
        this.hopService = hopService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Iterable<Hop> findAllHops() {
        return hopService.findAllHops();
    }
}
