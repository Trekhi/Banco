package unide.usb.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unide.usb.banco.domain.Usuario;

import java.util.Optional;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {

    //Validación d
    Optional<Usuario> findUsuarioByid(Integer id);
    Optional<Usuario> findUsuarioBydireccion(String direccion);
    Optional<Usuario> findUsuarioBycorreo(String correo);
}
