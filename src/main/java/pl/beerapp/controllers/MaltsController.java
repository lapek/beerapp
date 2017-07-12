package pl.beerapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.beerapp.entities.Malt;
import pl.beerapp.services.MaltService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/malts")
public class MaltsController {

    private final MaltService maltService;

    @Autowired
    public MaltsController(MaltService maltService) {
        this.maltService = maltService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Malt> findAllMalts() {
        return maltService.findAllMalts();
    }
}
