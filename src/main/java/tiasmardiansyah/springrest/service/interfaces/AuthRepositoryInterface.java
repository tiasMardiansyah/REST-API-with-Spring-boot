package tiasmardiansyah.springrest.service.interfaces;

import java.io.IOException;

import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;

import tiasmardiansyah.springrest.model.User;

public interface AuthRepositoryInterface {

    public ResponseEntity<?> login(String username, String password) throws IOException;

    public ResponseEntity<?> register(User user) throws JsonProcessingException;
}
