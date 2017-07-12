package pl.beerapp.services;

import pl.beerapp.entities.Malt;

import java.util.List;

public interface MaltService {
    List<Malt> findAllMalts();
}
