package tiasmardiansyah.springrest.services;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tiasmardiansyah.springrest.configurations.security.UserCredential;
import tiasmardiansyah.springrest.models.Barang;
import tiasmardiansyah.springrest.repositories.BarangRepository;
import tiasmardiansyah.springrest.services.interfaces.BarangService;

@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultBarangServiceImpl implements BarangService {

    //injected by spring IOC Container
    private final BarangRepository barangRepository;
    private final Authentication auth;

    @Override   
    public ResponseEntity<?> getAllBarang() {
        List<Barang> barangs = barangRepository.findAll();
        if (barangs.isEmpty()) return ResponseEntity.noContent().build();   
        
        return ResponseEntity.ok().body(barangs);
    }

    @Override
    public ResponseEntity<?> create(Barang object) {
        Barang newBarang = barangRepository.save(object);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBarang);
    }

    @Override
    public ResponseEntity<?> update(Integer id, Barang object) {
        
        boolean exist = barangRepository.existsById(id);
        if (!exist) return ResponseEntity.notFound().build();

        Barang updatedBarang = barangRepository.save(object);
        return ResponseEntity.ok().body(updatedBarang);
    }

    @Override
    public ResponseEntity<?> read(Integer id) {
        Barang barang = barangRepository.findById(id).orElse(null);
        if (barang == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok().body(barang);
    }

    @Override
    public ResponseEntity<?> delete(Integer id) {
        boolean exist = barangRepository.existsById(id);
        if (!exist) return ResponseEntity.notFound().build();

        barangRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
            "status", "item dengan ID: " + id + " berhasil dihapus"
        ));
    }

}
