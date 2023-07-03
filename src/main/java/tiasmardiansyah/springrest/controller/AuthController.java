package tiasmardiansyah.springrest.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;

import jakarta.validation.Valid;
import tiasmardiansyah.springrest.model.User;
import tiasmardiansyah.springrest.service.implementation.AuthRepositoryService;

@Controller
public class AuthController {

    @Autowired
    private AuthRepositoryService authRepositoryService;

    @PostMapping(path = "/auth/login")
    public ResponseEntity<String> login(@RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password) throws IOException {

        return authRepositoryService.login(username, password);

    }

    @PostMapping(path = "/auth/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> register(@ModelAttribute @Valid User user, BindingResult bindingResult)
            throws JsonProcessingException {

        return authRepositoryService.register(user, bindingResult);

    }

}
