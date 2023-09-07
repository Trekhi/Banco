package unide.usb.banco.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class UsuarioDTO {
    private Integer id;
    private String nombre;
    private String telefono;
    private String dirrecion;
    private String correo;
    private String contrasena;

}
