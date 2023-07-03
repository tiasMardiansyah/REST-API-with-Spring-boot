package tiasmardiansyah.springrest.service.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.fasterxml.jackson.core.JsonProcessingException;

import tiasmardiansyah.springrest.model.Barang;

public interface BarangServiceInterface {

    public ResponseEntity<String> getAllBarang() throws JsonProcessingException;

    public ResponseEntity<String> getBarangById(int id) throws JsonProcessingException;

    public ResponseEntity<String> createBarang(Barang barang, BindingResult bindingResult)
            throws JsonProcessingException;

    public ResponseEntity<String> updateBarang(int id, Barang barang, BindingResult bindingResult)
            throws JsonProcessingException;

    public ResponseEntity<String> deleteBarang(int id) throws JsonProcessingException;
}
