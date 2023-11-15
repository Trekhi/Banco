package unide.usb.banco.service;

import org.springframework.http.ResponseEntity;
import unide.usb.banco.dto.CuentaDTO;

import java.util.List;

public interface CuentaService {

    public CuentaDTO guardarNuevaCuenta(CuentaDTO cuentaDTO) throws Exception;
    List<CuentaDTO> mostrarTodos();
    ResponseEntity<Void> desactivarCuentaPorId(Integer cuentaId);
    ResponseEntity<Void> activarCuentaPorId(Integer cuentaId);
}
