package tiasmardiansyah.springrest.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    
    private int id;

    private String nama;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @Email @NotBlank
    private String email;

}
