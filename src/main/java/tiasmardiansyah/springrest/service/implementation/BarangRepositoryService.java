package tiasmardiansyah.springrest.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import tiasmardiansyah.springrest.model.Barang;
import tiasmardiansyah.springrest.model.ErrorModel;
import tiasmardiansyah.springrest.service.interfaces.BarangServiceInterface;

@Service
public class BarangRepositoryService implements BarangServiceInterface {

    // untuk sekarang saya gunakan dummy data
    private List<Barang> listBarang = new ArrayList<>();

    @Override
    public ResponseEntity<?> getAllBarang() {
        if (listBarang.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(listBarang);
    }

    @Override
    public ResponseEntity<?> getBarangById(int id) {
        if (listBarang.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        for (Barang barang : listBarang) {
            if (barang.getId() == id) {
                return ResponseEntity.ok().body(barang);
            }
        }

        ErrorModel error = new ErrorModel(HttpStatus.NOT_FOUND, "Tidak Ditemukan barang dengan ID => " + id);
        return ResponseEntity.status(error.getErrorCode()).body(error);
    }

    @Override
    public ResponseEntity<?> createBarang(Barang barang) {
        listBarang.add(barang);
        return ResponseEntity.status(HttpStatus.CREATED).body(barang);
    }

    @Override
    public ResponseEntity<?> updateBarang(int id, Barang barang) {
        int index = -1;
        for (Barang Barang : listBarang) {
            index++;
            if (Barang.getId() == id) {

                listBarang.remove(index);
                listBarang.add(barang);

                return ResponseEntity.ok().body(listBarang);
            }
        }

        ErrorModel error = new ErrorModel(HttpStatus.NOT_FOUND, "Tidak Ditemukan barang dengan ID => " + id);
        return ResponseEntity.status(error.getErrorCode()).body(error);
    }

    @Override
    public ResponseEntity<?> deleteBarang(int id) {
        if (listBarang.isEmpty())
            return ResponseEntity.noContent().build();

        int index = -1;
        for (Barang barang : listBarang) {
            index++;
            if (barang.getId() == id) {
                Barang deletedBarang = barang;
                listBarang.remove(index);
                return ResponseEntity.status(HttpStatus.OK).body(deletedBarang);
            }
        }

        ErrorModel error = new ErrorModel(HttpStatus.NOT_FOUND, "Tidak Ditemukan barang dengan ID =>" + id);
        return ResponseEntity.status(error.getErrorCode()).body(error);
    }

}
