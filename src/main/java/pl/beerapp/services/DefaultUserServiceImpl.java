package pl.beerapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.beerapp.entities.User;
import pl.beerapp.repositories.UserRepository;

@Service
public class DefaultUserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    @Override
    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public User findOne(String subject) {
        return this.userRepository.findOne(subject);
    }

    @Override
    public User save(User user) {
        return this.userRepository.save(user);
    }
}
