package tiasmardiansyah.springrest.controller.rest;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import tiasmardiansyah.springrest.model.User;
import tiasmardiansyah.springrest.service.implementation.AuthRepositoryService;

@RestController
public class AuthController {

    @Autowired
    private AuthRepositoryService authRepositoryService;

    @PostMapping(path = "/api/v1/auth/login")
    public ResponseEntity<?> login(@RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password) throws IOException {

        return authRepositoryService.login(username, password);

    }

    @PostMapping(path = "/api/v1/auth/register")
    public ResponseEntity<?> register(@ModelAttribute @Valid User user) {
        return authRepositoryService.register(user);
    }

}
