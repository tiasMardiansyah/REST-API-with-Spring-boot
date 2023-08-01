package tiasmardiansyah.springrest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import tiasmardiansyah.springrest.configurations.security.UserCredential;
import tiasmardiansyah.springrest.services.interfaces.UserService;

@RestController
@RequestMapping(path = "/api/v1/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService dao;
    
    @PostMapping(path = "register")
    public ResponseEntity<?> register(@ModelAttribute @Valid UserCredential user) {
        return dao.create(user);
    }

    @DeleteMapping(path = "delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        return dao.delete(id);
    }

    @PostMapping(path = "update/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id, @ModelAttribute @Valid UserCredential user) {
        return dao.update(id, user);
    }

    @GetMapping(path = "{id}") 
    public ResponseEntity<?> readUserInfo(@PathVariable(name = "id") Long id) {
        return dao.read(id);
    }
    
}
