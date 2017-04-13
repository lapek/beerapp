package pl.beerapp.security;

import com.nimbusds.jose.JOSEException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.beerapp.entities.User;
import pl.beerapp.repositories.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.core.Context;

@RestController
public class LocalAuthenticationController {

    @Autowired
    private final UserRepository userRepository;

    public LocalAuthenticationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping("/auth/login")
    public ResponseEntity login(@RequestBody @Valid final User user, @Context final HttpServletRequest request) throws JOSEException {
        final User foundUser = userRepository.findByUsername(user.getUsername());
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
        final User savedUser = userRepository.save(user);
        final Token token = AuthUtils.createToken(request.getRemoteHost(), savedUser.getId().toString());
        return new ResponseEntity<Token>(token, HttpStatus.CREATED);
    }

}
