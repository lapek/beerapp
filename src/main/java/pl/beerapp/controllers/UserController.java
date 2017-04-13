package pl.beerapp.controllers;

import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.beerapp.entities.User;
import pl.beerapp.security.AuthUtils;
import pl.beerapp.security.RoleEnum;
import pl.beerapp.repositories.UserRepository;
import pl.beerapp.entities.UserRole;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import java.security.Principal;
import java.text.ParseException;

@RestController
public class UserController {

    @Autowired
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping("/api/user")
    public ResponseEntity findUser(@Context final HttpServletRequest request)
            throws JOSEException {
        User foundUser = null;
        try {
            foundUser = getAuthUser(request);
            if (foundUser == null) {
                return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<User>(foundUser, HttpStatus.CREATED);
    }

    private User getAuthUser(HttpServletRequest request) throws JOSEException, ParseException {
        String subject = AuthUtils.getSubject(request.getHeader(AuthUtils.AUTH_HEADER_KEY));
        return userRepository.findOne(subject);
    }

}