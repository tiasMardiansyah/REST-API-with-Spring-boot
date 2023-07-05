package tiasmardiansyah.springrest.service.implementation;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import tiasmardiansyah.springrest.model.Barang;
import tiasmardiansyah.springrest.repository.BarangRepository;
import tiasmardiansyah.springrest.service.interfaces.BarangServiceInterface;

@Service
public class BarangService implements BarangServiceInterface {

    @Autowired
    private BarangRepository barangRepository;

    @Override
    public ResponseEntity<?> getAllBarang() {
        List<Barang> barangs = barangRepository.findAll();
        if (barangs.isEmpty()) throw new ResponseStatusException(HttpStatus.NO_CONTENT);

        return ResponseEntity.ok().body(barangs);
    }

    @Override
    public ResponseEntity<?> getBarangById(int id) {
        Barang barang = barangRepository.findById(id).orElse(null);
        if (Objects.isNull(barang)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "barang Tidak ditemukan");

        return ResponseEntity.ok().body(barang);
    }

    @Override
    public ResponseEntity<?> createBarang(Barang barang) {
        Barang newBarang = barangRepository.save(barang);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBarang);
    }

    @Override
    public ResponseEntity<?> updateBarang(int id, Barang barang) {

        //to-do buat masa depan, buat filter agar user hanya bisa mengupdate postingan nya sendiri

        boolean exist = barangRepository.existsById(id);
        if (!exist) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        Barang updatedBarang = barangRepository.save(barang);
        return ResponseEntity.ok().body(updatedBarang);
    }

    @Override
    public ResponseEntity<?> deleteBarang(int id) {

        //to-do buat masa depan, buat filter agar user hanya bisa menghapus postingan nya sendiri

        boolean exist = barangRepository.existsById(id);
        if (!exist) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        barangRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
            "status", "item dengan ID: " + id + " berhasil dihapus"
        ));

    }

}
