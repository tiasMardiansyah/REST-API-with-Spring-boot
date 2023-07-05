package tiasmardiansyah.springrest.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import tiasmardiansyah.springrest.model.Barang;

import tiasmardiansyah.springrest.service.interfaces.BarangServiceInterface;

@RestController
public class BarangController {

    @Autowired
    public BarangServiceInterface service;

    @GetMapping(path = "/api/v1/barang")
    public ResponseEntity<?> get() {
        return service.getAllBarang();
    }

    @GetMapping(path = "/api/v1/barang/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") int id) {
        return service.getBarangById(id);
    }

    @PostMapping(path = "/api/v1/barang")
    public ResponseEntity<?> post(@ModelAttribute @Valid Barang request) {
        return service.createBarang(request);
    }

    @PutMapping(path = "/api/v1/barang/{id}")
    public ResponseEntity<?> put(@PathVariable(name = "id") int id, @ModelAttribute @Valid Barang request) {
        return service.updateBarang(id, request);
    }

    @DeleteMapping(path = "/api/v1/barang/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        return service.deleteBarang(id);
    }
}
