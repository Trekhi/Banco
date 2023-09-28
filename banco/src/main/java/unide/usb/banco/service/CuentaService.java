package unide.usb.banco.service;

import unide.usb.banco.dto.CuentaDTO;

public interface CuentaService {

    public CuentaDTO guardarNuevaCuenta(CuentaDTO cuentaDTO) throws Exception;

}
