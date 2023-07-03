package tiasmardiansyah.springrest.service.implementation;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import tiasmardiansyah.springrest.model.User;
import tiasmardiansyah.springrest.service.interfaces.AuthRepositoryInterface;

@Service
public class AuthRepositoryService implements AuthRepositoryInterface {

    @Autowired
    public ObjectMapper objectMapper;

    @Override
    public ResponseEntity<String> login(String username, String password) throws IOException {
        // query to db
        if (username.equals("test") && password.equals("123")) {
            return ResponseEntity.ok().body("Accepted");
        }

        return ResponseEntity.badRequest().body("Username or Password is Incorrect");
    }

    @Override
    public ResponseEntity<String> register(User user, BindingResult bindingResult) throws JsonProcessingException {
        List<FieldError> InvalidData = bindingResult.getFieldErrors();

        if (!InvalidData.isEmpty()) {
            Map<String, String> errorList = new HashMap<>();
            InvalidData.forEach(notValidField -> {
                errorList.put(notValidField.getField(), notValidField.getDefaultMessage());
            });

            System.out.print(objectMapper.writeValueAsString(errorList));
            return ResponseEntity.badRequest().body(objectMapper.writeValueAsString(errorList));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(objectMapper.writeValueAsString(user));
    }

}
