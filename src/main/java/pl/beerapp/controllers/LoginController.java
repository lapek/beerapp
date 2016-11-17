package pl.beerapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class LoginController {

    @RequestMapping(value = "/users/login", method = RequestMethod.POST)
    public String userLogin() {

        return "redirect:/";
    }
}
