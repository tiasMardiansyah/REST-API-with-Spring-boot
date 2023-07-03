package tiasmardiansyah.springrest.service.implementation;

import java.util.ArrayList;
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

import tiasmardiansyah.springrest.model.Barang;
import tiasmardiansyah.springrest.model.ErrorModel;
import tiasmardiansyah.springrest.service.interfaces.BarangServiceInterface;

@Service
public class BarangRepositoryService implements BarangServiceInterface {

    // untuk sekarang saya gunakan dummy data
    private List<Barang> listBarang = new ArrayList<>();

    // converter data menjadi json
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public ResponseEntity<String> getAllBarang() throws JsonProcessingException {
        if (listBarang.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(objectMapper.writeValueAsString(listBarang));
    }

    @Override
    public ResponseEntity<String> getBarangById(int id) throws JsonProcessingException {
        if (listBarang.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        for (Barang barang : listBarang) {
            if (barang.getId() == id) {
                return ResponseEntity.ok().body(objectMapper.writeValueAsString(barang));
            }
        }

        ErrorModel error = new ErrorModel(HttpStatus.NOT_FOUND, "Tidak Ditemukan barang dengan ID => " + id);
        return ResponseEntity.status(error.getErrorCode()).body(objectMapper.writeValueAsString(error));
    }

    @Override
    public ResponseEntity<String> createBarang(Barang barang, BindingResult bindingResult)
            throws JsonProcessingException {

        List<FieldError> errorField = bindingResult.getFieldErrors();
        if (!errorField.isEmpty()) {
            Map<String, String> errorMessage = new HashMap<>();
            errorField.forEach(field -> {
                errorMessage.put(field.getField(), field.getDefaultMessage());
            });

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectMapper.writeValueAsString(errorMessage));
        }

        listBarang.add(barang);
        return ResponseEntity.status(HttpStatus.CREATED).body(objectMapper.writeValueAsString(barang));
    }

    @Override
    public ResponseEntity<String> updateBarang(int id, Barang barang, BindingResult bindingResult)
            throws JsonProcessingException {
        if (listBarang.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<FieldError> errorField = bindingResult.getFieldErrors();
        if (!errorField.isEmpty()) {

            Map<String, String> errorMessage = new HashMap<>();
            errorField.forEach(field -> {
                errorMessage.put(field.getField(), field.getDefaultMessage());
            });

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectMapper.writeValueAsString(errorMessage));
        }

        int index = -1;
        for (Barang Barang : listBarang) {
            index++;
            if (Barang.getId() == id) {

                listBarang.remove(index);
                listBarang.add(barang);

                return ResponseEntity.ok().body(objectMapper.writeValueAsString(listBarang));
            }
        }

        ErrorModel error = new ErrorModel(HttpStatus.NOT_FOUND, "Tidak Ditemukan barang dengan ID => " + id);
        return ResponseEntity.status(error.getErrorCode()).body(objectMapper.writeValueAsString(error));
    }

    @Override
    public ResponseEntity<String> deleteBarang(int id) throws JsonProcessingException {
         if (listBarang.isEmpty())
            return ResponseEntity.noContent().build();

        int index = -1;
        for (Barang barang : listBarang) {
            index++;
            if (barang.getId() == id) {
                Barang deletedBarang = barang;
                listBarang.remove(index);
                return ResponseEntity.status(HttpStatus.OK).body(objectMapper.writeValueAsString(deletedBarang));
            }
        }

        ErrorModel error = new ErrorModel(HttpStatus.NOT_FOUND, "Tidak Ditemukan barang dengan ID =>" + id);
        return ResponseEntity.status(error.getErrorCode()).body(objectMapper.writeValueAsString(error));
    }

}
