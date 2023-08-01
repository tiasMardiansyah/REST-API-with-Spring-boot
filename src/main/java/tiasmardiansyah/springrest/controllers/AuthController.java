package tiasmardiansyah.springrest.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import tiasmardiansyah.springrest.models.UserRegister;
import tiasmardiansyah.springrest.services.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody @Valid UserRegister userRegister) {
        return authService.register(userRegister);
    }

    @PostMapping("login")
    public ResponseEntity<?> login(
        @RequestParam(name = "username") String username,
        @RequestParam(name = "password") String password
    ) {
        return authService.login(username, password);
    }
}
