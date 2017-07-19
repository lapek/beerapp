package pl.beerapp.security;

import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.beerapp.entities.User;
import pl.beerapp.entities.enumeration.UserRole;
import pl.beerapp.repositories.UserRepository;
import pl.beerapp.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.core.Context;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/auth")
public class LocalAuthenticationController {

    private final UserService userService;

    @Autowired
    public LocalAuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody @Valid final User user, @Context final HttpServletRequest request) throws JOSEException {
        final User foundUser = userService.findByUsername(user.getUsername());
        if (foundUser != null
                && PasswordService.checkPassword(user.getPassword(), foundUser.getPassword())) {
            final Token token = AuthUtils.createToken(request.getRemoteHost(), foundUser.getId().toString(), foundUser.getUserRole().toString());
            return new ResponseEntity<>(token, HttpStatus.OK);
        }
        return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity signup(@RequestBody @Valid final User user)
            throws JOSEException {
        if (userService.findByUsername(user.getUsername()) != null) {
            throw new RuntimeException("Username already exist");
        }
        user.setPassword(PasswordService.hashPassword(user.getPassword()));
        user.setUserRole(UserRole.USER);
        return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.CREATED);
    }

}
