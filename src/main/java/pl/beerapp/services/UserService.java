package pl.beerapp.services;

import pl.beerapp.entities.User;

public interface UserService {
    User findByEmail(String email);
    User findByUsername(String username);
    User findOne(String subject);
    User save(User user);
}
