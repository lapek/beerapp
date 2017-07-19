package pl.beerapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.beerapp.entities.User;
import pl.beerapp.repositories.UserRepository;

import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(Long id) {
        return userRepository.findOne(id);
    }

    public User findByUsername(String name) {
        return userRepository.findByUsername(name);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void updateUser(User user) {
        saveUser(user);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

}
