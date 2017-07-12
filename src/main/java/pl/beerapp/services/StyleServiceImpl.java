package pl.beerapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.beerapp.entities.Style;
import pl.beerapp.repositories.StylesRepository;

import java.util.List;

@Service("styleService")
@Transactional
public class StyleServiceImpl implements StyleService {

    private final StylesRepository stylesRepository;

    @Autowired
    public StyleServiceImpl(StylesRepository stylesRepository) {
        this.stylesRepository = stylesRepository;
    }

    public List<Style> findAllStyles() {
        return stylesRepository.findAll();
    }
}
