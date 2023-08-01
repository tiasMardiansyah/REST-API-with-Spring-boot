package tiasmardiansyah.springrest.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import tiasmardiansyah.springrest.models.Barang;
import tiasmardiansyah.springrest.services.interfaces.BarangService;

@RestController
@RequestMapping(path = "api/v1/barang", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class BarangController {

    private final BarangService barangService;

    @GetMapping()
    public ResponseEntity<?> get() {
        return barangService.getAllBarang();
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") int id) {
        return barangService.read(id);
    }

    @PostMapping()
    public ResponseEntity<?> post(@ModelAttribute @Valid Barang request) {
        return barangService.create(request);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<?> put(@PathVariable(name = "id") int id, @ModelAttribute @Valid Barang request) {
        return barangService.update(id, request);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        return barangService.delete(id);
    }
}
