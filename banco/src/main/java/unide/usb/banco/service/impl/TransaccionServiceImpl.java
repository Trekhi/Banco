package unide.usb.banco.service.impl;

import org.springframework.stereotype.Service;
import unide.usb.banco.domain.Cuenta;
import unide.usb.banco.domain.Transaccion;
import unide.usb.banco.dto.TransaccionDTO;
import unide.usb.banco.mapper.TransaccionMapper;
import unide.usb.banco.repository.CuentaRepository;
import unide.usb.banco.repository.TransaccionRepository;
import unide.usb.banco.service.TransaccionService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class TransaccionServiceImpl implements TransaccionService {

    private final TransaccionRepository transaccionRepository;
    //Depende de otra entidad
    private final CuentaRepository cuentaRepository;

    public TransaccionServiceImpl(TransaccionRepository transaccionRepository, CuentaRepository cuentaRepository) {
        this.transaccionRepository = transaccionRepository;
        this.cuentaRepository = cuentaRepository;
    }

    @Override
    public TransaccionDTO guardarNuevoTransaccion(TransaccionDTO transaccionDTO) throws Exception {
        if (transaccionDTO == null)
        {
            throw new Exception("La transaccion es Nula");
        }
        if(transaccionDTO.getCuentaId() == null || transaccionDTO.getCuentaId() == 0){
            throw new Exception("No encuentra cuenta");
        }
        if (transaccionDTO.getConsignacion()== null || transaccionDTO.getConsignacion().equals(BigDecimal.ZERO))
        {
            throw new Exception("Consignacion no encontrado");
        }
        if (transaccionDTO.getFechaenvio()== null || transaccionDTO.getFechaenvio().equals(""))
        {
            throw new Exception("Fecha de envio vacio");
        }

        // Validar existencia de cuenta

        Optional<Cuenta> cuentaOptional = cuentaRepository.findCuentaById(transaccionDTO.getCuentaId());
        if(cuentaOptional.isEmpty()){
            throw new Exception(String.format("No se puede realizar la transacción porque no se encuentra la cuenta referenciada con id: %d"
                    , transaccionDTO.getCuentaId()));
        }
        Transaccion transaccion = TransaccionMapper.dtoToDomain(transaccionDTO);
        transaccion.setCuenta(cuentaOptional.get());
        transaccion = transaccionRepository.save(transaccion);

        return TransaccionMapper.domainToDto(transaccion);

    }

    @Override
    public List<TransaccionDTO> buscarTodas() {
        return TransaccionMapper.domainToDtoList(transaccionRepository.findAll());
    }
}
