package tiasmardiansyah.springrest.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "barangs")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Barang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama_barang")
    @NotBlank
    private String namaBarang;

    @Column(name = "deskripsi_barang")
    @NotBlank
    private String deskripsiBarang;

    private int qty;

}
