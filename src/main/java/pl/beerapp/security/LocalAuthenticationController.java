package pl.beerapp.security;

import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.beerapp.entities.User;
import pl.beerapp.repositories.UserRepository;
import pl.beerapp.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.core.Context;

@RestController
public class LocalAuthenticationController {

    private final UserService userService;

    @Autowired
    public LocalAuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/auth/login")
    public ResponseEntity login(@RequestBody @Valid final User user, @Context final HttpServletRequest request) throws JOSEException {
        final User foundUser = userService.findByUsername(user.getUsername());
        if (foundUser != null
                && PasswordService.checkPassword(user.getPassword(), foundUser.getPassword())) {
            final Token token = AuthUtils.createToken(request.getRemoteHost(), foundUser.getId().toString());
            return new ResponseEntity<Token>(token, HttpStatus.OK);
        }
        return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping("/auth/signup")
    public ResponseEntity signup(@RequestBody @Valid final User user, @Context final HttpServletRequest request)
            throws JOSEException {
        user.setPassword(PasswordService.hashPassword(user.getPassword()));
        final User savedUser = userService.saveUser(user);
        final Token token = AuthUtils.createToken(request.getRemoteHost(), savedUser.getId().toString());
        return new ResponseEntity<Token>(token, HttpStatus.CREATED);
    }

}
