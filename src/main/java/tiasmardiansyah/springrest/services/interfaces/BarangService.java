package tiasmardiansyah.springrest.services.interfaces;

import org.springframework.http.ResponseEntity;

import tiasmardiansyah.springrest.models.Barang;

public interface BarangService extends CrudService<Barang,Integer>{
    public ResponseEntity<?> getAllBarang();
}
