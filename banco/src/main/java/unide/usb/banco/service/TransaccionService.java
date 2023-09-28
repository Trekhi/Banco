package unide.usb.banco.service;

import unide.usb.banco.dto.TransaccionDTO;

public interface TransaccionService {

    public TransaccionDTO guardarNuevoTransaccion(TransaccionDTO transaccionDTO) throws Exception;


}
