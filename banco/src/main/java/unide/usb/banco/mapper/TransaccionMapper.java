package unide.usb.banco.mapper;

import unide.usb.banco.domain.Transaccion;
import unide.usb.banco.dto.TransaccionDTO;

import java.util.List;

public class TransaccionMapper {

    public static Transaccion dtoToDomain (TransaccionDTO transaccionDTO){
        return Transaccion.builder()
                .id(transaccionDTO.getId())
                .consignacion((transaccionDTO.getConsignacion()))
                .fechaenvio((transaccionDTO.getFechaenvio()))
                .build();
    }

    public static TransaccionDTO domainToDto (Transaccion transaccion){
        return TransaccionDTO.builder()
                .id(transaccion.getId())
                //Para las llaves foraneas.
                .cuentaId((transaccion.getCuenta() == null ) ?
                    null : transaccion.getCuenta().getId())
                .consignacion((transaccion.getConsignacion()))
                .fechaenvio((transaccion.getFechaenvio()))
                .build();
    }

    public static List<Transaccion> dtoToDomainList (List<TransaccionDTO> transaccionDTOS){
        return transaccionDTOS.stream().map(TransaccionMapper::dtoToDomain).toList();

    }

    public static  List<TransaccionDTO> domainToDtoList (List<Transaccion> transacciones){
        return transacciones.stream().map(TransaccionMapper::domainToDto).toList();
    }
}
