package unide.usb.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unide.usb.banco.domain.Usuario;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
}
