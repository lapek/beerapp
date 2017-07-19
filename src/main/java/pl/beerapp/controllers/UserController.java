package pl.beerapp.controllers;

import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.beerapp.entities.User;
import pl.beerapp.security.AuthUtils;
import pl.beerapp.repositories.UserRepository;
import pl.beerapp.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.core.Context;
import java.security.Principal;
import java.text.ParseException;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/profile")
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

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public User getUser(Principal principal) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedUsername = auth.getName();
        return userService.findByUsername(loggedUsername);
    }

    private User getAuthUser(HttpServletRequest request) throws JOSEException, ParseException {
        String subject = AuthUtils.getSubject(request.getHeader(AuthUtils.AUTH_HEADER_KEY));
        return userService.findById(Long.valueOf(subject));
    }

}