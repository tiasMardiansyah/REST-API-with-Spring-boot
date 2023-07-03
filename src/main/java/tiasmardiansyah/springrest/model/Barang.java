package tiasmardiansyah.springrest.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Barang {
     
    @NotNull
    private int id;

    @NotBlank
    private String namaBarang; 

    @NotBlank
    private String deskripsiBarang;

    private int qtyBarang;
}
