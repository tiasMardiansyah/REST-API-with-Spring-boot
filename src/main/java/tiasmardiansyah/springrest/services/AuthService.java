package tiasmardiansyah.springrest.services;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tiasmardiansyah.springrest.configurations.security.Role;
import tiasmardiansyah.springrest.configurations.security.UserCredential;
import tiasmardiansyah.springrest.models.UserRegister;
import tiasmardiansyah.springrest.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authManager;
    private final TokenService tokenService;
    
    public ResponseEntity<?> register(UserRegister register) {
        UserCredential newUser = new UserCredential();
        newUser.setPassword(encoder.encode(register.getPassword()));
        newUser.setUsername(register.getUsername());
        newUser.setRole(Role.ROLE_ADMIN);

        repository.save(newUser);

        return ResponseEntity.ok().body("Account Registered");
    }

    //this will return a map with key token and value of token
    public ResponseEntity<?> login(String username, String password) {
        var auth = authManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                username,
                password

            )
        );

        String token = tokenService.generateToken(auth);
        return ResponseEntity.ok().body(
            Map.of("Token", token)
        );
    }    

}
