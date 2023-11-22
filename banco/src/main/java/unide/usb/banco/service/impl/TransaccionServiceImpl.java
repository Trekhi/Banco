package unide.usb.banco.service.impl;

import org.springframework.stereotype.Service;
import unide.usb.banco.domain.Cuenta;
import unide.usb.banco.domain.Transaccion;
import unide.usb.banco.dto.CuentaDTO;
import unide.usb.banco.dto.TransaccionDTO;
import unide.usb.banco.mapper.CuentaMapper;
import unide.usb.banco.mapper.TransaccionMapper;
import unide.usb.banco.repository.CuentaRepository;
import unide.usb.banco.repository.TransaccionRepository;
import unide.usb.banco.service.TransaccionService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.apache.commons.lang3.BooleanUtils.TRUE;
import static org.apache.commons.lang3.BooleanUtils.or;

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

    @Override
    public TransaccionDTO mandarDinero(TransaccionDTO transaccionDTO, Integer cuenta) throws Exception {

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

        Optional<Cuenta> cuentaOptional = cuentaRepository.findCuentaById(transaccionDTO.getCuentaId());
        if(cuentaOptional.isEmpty()){
            throw new Exception(String.format("No se puede realizar la transacción porque no se encuentra la cuenta referenciada con id: %d"
                    , transaccionDTO.getCuentaId()));
        }

        Optional<Cuenta> cuentaDestino = cuentaRepository.findCuentaById(cuenta);

        if(cuentaDestino.isEmpty()){
            throw new Exception(String.format("No se puede realizar la transacción porque no se encuentra la cuenta de destino con id: %d"
                    , cuenta));
        }

        // Comprobar que la cuenta de salida tiene el dinero suficiente

        CuentaDTO cuentaI = CuentaMapper.domainToDto(cuentaRepository.getReferenceById(transaccionDTO.getCuentaId()));
        CuentaDTO cuentaF = CuentaMapper.domainToDto(cuentaRepository.getReferenceById(cuenta));

        double disponible = cuentaI.getFondos().doubleValue();
        double cantidadEnviar = transaccionDTO.getConsignacion().doubleValue();

        if ( disponible < cantidadEnviar){
            throw  new Exception("La cantidad a enviar supera su saldo disponible");
        }

        BigDecimal menosSaldo = new BigDecimal(disponible-cantidadEnviar);
        BigDecimal masSaldo = new BigDecimal(cuentaF.getFondos().doubleValue() + cantidadEnviar);

        cuentaI.setFondos(menosSaldo);
        cuentaF.setFondos(masSaldo);

        Cuenta cuenta1 = CuentaMapper.dtoToDomain(cuentaI);
        cuenta1.setUsuario(cuentaOptional.get().getUsuario());
        Cuenta cuenta2 = CuentaMapper.dtoToDomain(cuentaF);
        cuenta2.setUsuario(cuentaDestino.get().getUsuario());

        cuentaRepository.save(cuenta1);
        cuentaRepository.save(cuenta2);

        Transaccion transaccion = TransaccionMapper.dtoToDomain(transaccionDTO);
        transaccion.setCuenta(cuentaOptional.get());
        transaccion = transaccionRepository.save(transaccion);

        return TransaccionMapper.domainToDto(transaccion);
    }
}
