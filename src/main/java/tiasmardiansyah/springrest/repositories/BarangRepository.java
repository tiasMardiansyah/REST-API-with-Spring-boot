package tiasmardiansyah.springrest.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tiasmardiansyah.springrest.models.Barang;

public interface BarangRepository extends JpaRepository<Barang,Integer>{
    List<Barang> findByNamaBarang(String name);

    

}
