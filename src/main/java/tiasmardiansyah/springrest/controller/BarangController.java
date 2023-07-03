package tiasmardiansyah.springrest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.fasterxml.jackson.core.JsonProcessingException;

import jakarta.validation.Valid;
import tiasmardiansyah.springrest.model.Barang;
import tiasmardiansyah.springrest.service.implementation.BarangRepositoryService;

@Controller
public class BarangController {

    @Autowired
    public BarangRepositoryService service;

    @GetMapping(path = "/barang", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> get() throws JsonProcessingException {
        return service.getAllBarang();
    }

    @GetMapping(path = "/barang/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getById(@PathVariable(name = "id") int id) throws JsonProcessingException {
        return service.getBarangById(id);
    }

    @PostMapping(path = "/barang", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> post(@ModelAttribute @Valid Barang request, BindingResult bindingResult)
            throws JsonProcessingException {

        return service.createBarang(request, bindingResult);
    }

    @PutMapping(path = "/barang/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateBarang(@PathVariable(name = "id") int id, @ModelAttribute @Valid Barang request,
            BindingResult bindingResult) throws JsonProcessingException {

        return service.updateBarang(id, request, bindingResult);

    }

    @DeleteMapping(path = "/barang/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteBarang(@PathVariable(name = "id") int id) throws JsonProcessingException {
        return service.deleteBarang(id);
    }
}
