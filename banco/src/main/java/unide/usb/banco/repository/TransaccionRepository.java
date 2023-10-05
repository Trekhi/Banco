package unide.usb.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unide.usb.banco.domain.Transaccion;

import java.util.Optional;

@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion, Integer> {
    //Metodos para hallar la existencia de un elemento repetido.
   Optional<Transaccion> findTransaccionByid(Integer id);
}
