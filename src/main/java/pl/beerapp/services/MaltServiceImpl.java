package pl.beerapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.beerapp.entities.Malt;
import pl.beerapp.repositories.MaltsRepository;

import java.util.List;

@Service("maltService")
@Transactional
public class MaltServiceImpl implements MaltService{

    private final MaltsRepository maltsRepository;

    @Autowired
    public MaltServiceImpl(MaltsRepository maltsRepository) {
        this.maltsRepository = maltsRepository;
    }

    public List<Malt> findAllMalts() {
        return maltsRepository.findAll();
    }
}
