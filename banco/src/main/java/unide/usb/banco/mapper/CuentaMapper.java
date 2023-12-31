package unide.usb.banco.mapper;

import unide.usb.banco.domain.Cuenta;
import unide.usb.banco.dto.CuentaDTO;

import java.util.List;

public class CuentaMapper {
    public static Cuenta dtoToDomain (CuentaDTO cuentaDTO) {
        return Cuenta.builder()
                .id(cuentaDTO.getId())
                .fondos(cuentaDTO.getFondos())
                .fechaapertura(cuentaDTO.getFechaapertura())
                .tipocuenta(cuentaDTO.getTipocuenta())
                .activo(cuentaDTO.isActivo())
                .build();
    }

    public static CuentaDTO domainToDto (Cuenta cuenta){
        return CuentaDTO.builder()
                .id(cuenta.getId())
                //Foraneaa
                .usuarioId((cuenta.getUsuario() == null ) ?
                    null : cuenta.getUsuario().getId())
                .fondos(cuenta.getFondos())
                .fechaapertura(cuenta.getFechaapertura())
                .tipocuenta(cuenta.getTipocuenta())
                .activo(cuenta.isActivo())
                .build();
    }
    public static List<Cuenta> dtoToDomainList (List<CuentaDTO> cuentaDTOS){
        return cuentaDTOS.stream().map(CuentaMapper::dtoToDomain).toList();
    }

    public static List<CuentaDTO> domainToDtoList (List<Cuenta> cuentas){
        return cuentas.stream().map(CuentaMapper::domainToDto).toList();
    }
}