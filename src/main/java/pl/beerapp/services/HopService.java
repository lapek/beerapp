package pl.beerapp.services;

import pl.beerapp.entities.Hop;

import java.util.List;

public interface HopService {
    List<Hop> findAllHops();
}
