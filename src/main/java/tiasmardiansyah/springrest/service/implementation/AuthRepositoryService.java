package tiasmardiansyah.springrest.service.implementation;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import tiasmardiansyah.springrest.model.User;
import tiasmardiansyah.springrest.service.interfaces.AuthRepositoryInterface;

@Service
public class AuthRepositoryService implements AuthRepositoryInterface {

    @Autowired
    public ObjectMapper objectMapper;

    @Override
    public ResponseEntity<?> login(String username, String password) throws IOException {
        // query to db
        if (username.equals("test") && password.equals("123")) {
            return ResponseEntity.ok().body("Accepted");
        }

        return ResponseEntity.badRequest().body("Username or Password is Incorrect");
    }

    @Override
    public ResponseEntity<?> register(User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

}
