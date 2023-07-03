package tiasmardiansyah.springrest.service.interfaces;

import org.springframework.http.ResponseEntity;

import tiasmardiansyah.springrest.model.Barang;

public interface BarangServiceInterface {

    public ResponseEntity<?> getAllBarang();

    public ResponseEntity<?> getBarangById(int id);

    public ResponseEntity<?> createBarang(Barang barang);

    public ResponseEntity<?> updateBarang(int id, Barang barang);

    public ResponseEntity<?> deleteBarang(int id);
}
