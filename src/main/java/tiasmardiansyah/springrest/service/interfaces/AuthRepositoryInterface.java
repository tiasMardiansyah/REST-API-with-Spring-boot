package tiasmardiansyah.springrest.service.interfaces;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.fasterxml.jackson.core.JsonProcessingException;

import tiasmardiansyah.springrest.model.User;

public interface AuthRepositoryInterface {

    public ResponseEntity<String> login(String username, String password) throws IOException;

    public ResponseEntity<String> register(User user, BindingResult bindingResult)
            throws JsonProcessingException;
}
