package unide.usb.banco.service.impl;

import org.springframework.stereotype.Service;
import unide.usb.banco.domain.Transaccion;
import unide.usb.banco.dto.TransaccionDTO;
import unide.usb.banco.mapper.TransaccionMapper;
import unide.usb.banco.repository.TransaccionRepository;
import unide.usb.banco.service.TransaccionService;

@Service
public class TransaccionServiceImpl implements TransaccionService {

    private final TransaccionRepository transaccionRepository;

    public TransaccionServiceImpl(TransaccionRepository transaccionRepository) {
        this.transaccionRepository = transaccionRepository;
    }

    @Override
    public TransaccionDTO guardarNuevoTransaccion(TransaccionDTO transaccionDTO) throws Exception {
        if (transaccionDTO == null)
        {
            throw new Exception("La transaccion es Nula");
        }

        if (transaccionDTO.getCuentaid()== null || transaccionDTO.getCuentaid().equals(""))
        {
            throw new Exception("Cuenta id errada");
        }

        if (transaccionDTO.getConsignacion()== null || transaccionDTO.getConsignacion().equals(""))
        {
            throw new Exception("Consignacion no encontrado");
        }

        if (transaccionDTO.getFechaenvio()== null || transaccionDTO.getFechaenvio().equals(""))
        {
            throw new Exception("Fecha de envio vacio");
        }

        Transaccion transaccion = TransaccionMapper.dtoToDomain(transaccionDTO);
        transaccion = transaccionRepository.save(transaccion);
        transaccionDTO = TransaccionMapper.domainToDto(transaccion);

        return transaccionDTO;

    }
}
