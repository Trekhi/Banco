package unide.usb.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unide.usb.banco.domain.Cuenta;

import java.util.Optional;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Integer> {
    //No estoy seguro
    Optional<Cuenta> findCuentaById(Integer id);

}
