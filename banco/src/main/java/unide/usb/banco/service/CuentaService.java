package unide.usb.banco.service;

import unide.usb.banco.dto.CuentaDTO;

import java.util.List;

public interface CuentaService {

    public CuentaDTO guardarNuevaCuenta(CuentaDTO cuentaDTO) throws Exception;
    List<CuentaDTO> mostrarTodos();
}
