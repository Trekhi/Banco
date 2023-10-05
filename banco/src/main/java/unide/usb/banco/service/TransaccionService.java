package unide.usb.banco.service;

import unide.usb.banco.dto.TransaccionDTO;

import java.util.List;

public interface TransaccionService {

    TransaccionDTO guardarNuevoTransaccion(TransaccionDTO transaccionDTO) throws Exception;
    List<TransaccionDTO> buscarTodas();

}
