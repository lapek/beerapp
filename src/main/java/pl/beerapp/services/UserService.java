package pl.beerapp.services;

import pl.beerapp.entities.User;

import java.util.List;

public interface UserService {
    User findById(Long id);

    User findByUsername(String name);

    User saveUser(User user);

    void updateUser(User user);

    List<User> findAllUsers();

}
