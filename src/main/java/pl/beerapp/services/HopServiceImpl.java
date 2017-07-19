package pl.beerapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.beerapp.entities.Hop;
import pl.beerapp.repositories.HopsRepository;

import java.util.List;

@Service("hopService")
@Transactional
public class HopServiceImpl implements HopService {

    private final HopsRepository hopsRepository;

    @Autowired
    public HopServiceImpl(HopsRepository hopsRepository) {
        this.hopsRepository = hopsRepository;
    }

    public List<Hop> findAllHops() {
        return hopsRepository.findAll();
    }
}
