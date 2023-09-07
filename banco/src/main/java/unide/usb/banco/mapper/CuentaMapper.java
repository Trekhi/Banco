package unide.usb.banco.mapper;

import unide.usb.banco.domain.Cuenta;
import unide.usb.banco.dto.CuentaDTO;

import java.util.List;

public class CuentaMapper {
    public Cuenta dtoToDomain (CuentaDTO cuentaDTO) {
        return Cuenta.builder()
                .id(cuentaDTO.getId())
                .usuario(cuentaDTO.getUsuarioid())
                .fondos(cuentaDTO.getFondos())
                .fechaApertura(cuentaDTO.getFechaApertura())
                .tipoCuenta(cuentaDTO.getTipoCuenta())
                .build()
    }
}

public List<Cuenta> dtoToDomain (List<CuentaDTO> cuentaDTOS){
    return cuentaDTOS.stream().map(CuentaMapper)
}