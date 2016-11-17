package pl.beerapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignupController {

    @RequestMapping(value = "/users/signup", method = RequestMethod.POST)
    public String userSignup() {

        return "redirect:/";
    }
}
