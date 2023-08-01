package tiasmardiansyah.springrest.services.interfaces;

import org.springframework.http.ResponseEntity;

//T adalah class nya contoh : Class Barang
//V adalah tipe data primary key yang digunakan : Int, Long 
public interface CrudService<T,V> {
    
    public ResponseEntity<?> create(T request);

    public ResponseEntity<?> update(V id, T request);

    public ResponseEntity<?> read(V id);

    public ResponseEntity<?> delete(V id);
}
