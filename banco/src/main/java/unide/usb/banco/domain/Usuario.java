package unide.usb.banco.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 15)
    private String telefono;

    @Column(nullable = false, length = 100)
    private String dirrecion;

    @Column(nullable = false, length = 50)
    private String correo;

    @Column(nullable = false, length = 100)
    private String contrasena;


}

