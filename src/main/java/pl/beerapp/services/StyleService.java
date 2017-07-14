package pl.beerapp.services;

import pl.beerapp.entities.Style;

import java.util.List;

public interface StyleService {
    List<Style> findAllStyles();

    Style findOneByName(String name);
}
