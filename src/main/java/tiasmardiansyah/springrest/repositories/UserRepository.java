package tiasmardiansyah.springrest.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tiasmardiansyah.springrest.configurations.security.UserCredential;

public interface UserRepository extends JpaRepository<UserCredential,Long>{
    Optional<UserCredential> findByUsername(String string);
}
