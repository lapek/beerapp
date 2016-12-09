package pl.beerapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.beerapp.entities.Malts;
import pl.beerapp.repositories.MaltsRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(value = "/api/malts")
public class MaltsController {

    private MaltsRepository maltsRepository;

    @Autowired
    public void setMaltsRepository(MaltsRepository maltsRepository) {
        this.maltsRepository = maltsRepository;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Iterable <Malts> findAllMalts() {
        return maltsRepository.findAll();
    }
}
