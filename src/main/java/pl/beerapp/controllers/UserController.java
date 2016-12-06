package pl.beerapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.beerapp.entities.User;
import pl.beerapp.security.PasswordCrypto;
import pl.beerapp.security.RoleEnum;
import pl.beerapp.repositories.UserRepository;
import pl.beerapp.entities.UserRole;

import java.security.Principal;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepo;

    private final PasswordCrypto passwordCrypto;

    @Autowired
    public UserController(UserRepository userRepo, PasswordCrypto passwordCrypto) {
        this.userRepo = userRepo;
        this.passwordCrypto = passwordCrypto;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody User user) {

        user.setPassword(passwordCrypto.encrypt(user.getPassword()));

        //create a new user with basic user privileges
        user.getRoles().add(new UserRole(RoleEnum.USER.toString(), user));

        if (userRepo.save(user) != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public Principal getUser(Principal user){
        return user;
    }

}