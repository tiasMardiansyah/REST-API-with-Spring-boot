package tiasmardiansyah.springrest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;

import tiasmardiansyah.springrest.configurations.security.UserCredential;
import tiasmardiansyah.springrest.repositories.UserRepository;
import tiasmardiansyah.springrest.services.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<?> create(UserCredential object) {
        if (userRepository.existsById(object.getId())) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Addd");
        }
        return null;
    }

    @Override
    public ResponseEntity<?> update(Long id, UserCredential object) {
        
        if (!userRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        //untuk jaga-jaga apabila id tidak sesuai dengan form
        object.setId(id);
        UserCredential newUser = userRepository.save(object);
        return ResponseEntity.ok().body(newUser);
    }

    @Override
    public ResponseEntity<?> read(Long id) {
        UserCredential user = userRepository.findById(id).orElse(null);
        return user != null
                ? ResponseEntity.ok().body(user)
                : ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<?> delete(Long id) {

        if (!userRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        userRepository.deleteById(id);
        return ResponseEntity.ok().body("Account Deleted");
    }
}
