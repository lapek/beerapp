package pl.beerapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.beerapp.entities.Yeast;
import pl.beerapp.repositories.YeastRepository;

import java.util.List;

@Service("yeastService")
@Transactional
public class YeastServiceImpl implements YeastService {

    private final YeastRepository yeastRepository;

    @Autowired
    public YeastServiceImpl(YeastRepository yeastRepository) {
        this.yeastRepository = yeastRepository;
    }

    public List<Yeast> findAllYeasts() {
        return yeastRepository.findAll();
    }
}
