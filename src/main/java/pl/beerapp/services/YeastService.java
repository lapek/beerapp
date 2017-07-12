package pl.beerapp.services;

import pl.beerapp.entities.Yeast;

import java.util.List;

public interface YeastService {
    List<Yeast> findAllYeasts();
}
