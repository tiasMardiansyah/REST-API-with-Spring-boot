package tiasmardiansyah.springrest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tiasmardiansyah.springrest.model.Barang;

public interface BarangRepository extends JpaRepository<Barang,Integer>{
    List<Barang> findByNamaBarang(String name);

}
