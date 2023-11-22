package unide.usb.banco.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import unide.usb.banco.domain.Cuenta;
import unide.usb.banco.domain.Usuario;
import unide.usb.banco.dto.CuentaDTO;
import unide.usb.banco.dto.TransaccionDTO;
import unide.usb.banco.mapper.CuentaMapper;
import unide.usb.banco.mapper.TransaccionMapper;
import unide.usb.banco.repository.CuentaRepository;
import unide.usb.banco.repository.TransaccionRepository;
import unide.usb.banco.repository.UsuarioRepository;
import unide.usb.banco.service.CuentaService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CuentaServiceImpl implements CuentaService {

    private final CuentaRepository cuentaRepository;
    //Depende de otra entidad
    private final UsuarioRepository usuarioRepository;

    private final TransaccionRepository transaccionRepository;

    public CuentaServiceImpl(CuentaRepository cuentaRepository, UsuarioRepository usuarioRepository, TransaccionRepository transaccionRepository) {
        this.cuentaRepository = cuentaRepository;
        this.usuarioRepository = usuarioRepository;
        this.transaccionRepository = transaccionRepository;
    }

    @Override
    public CuentaDTO guardarNuevaCuenta(CuentaDTO cuentaDTO) throws Exception {

        if (cuentaDTO == null) {
            throw new Exception("La cuenta es Nula");
        }
        if (cuentaDTO.getUsuarioId() == null || cuentaDTO.getUsuarioId() == 0){
            throw new Exception("no esta ligada aún Usuario");
        }
        if (cuentaDTO.getFondos()== null || cuentaDTO.getFondos().equals(BigDecimal.ZERO))
        {
            throw new Exception("Asignes la cantidad de fondos a registrar");
        }
        if (cuentaDTO.getFechaapertura()== null || cuentaDTO.getFechaapertura().equals(""))
        {
            throw new Exception("Fecha de apertura vacia");
        }
        if (cuentaDTO.getTipocuenta() == null || cuentaDTO.getTipocuenta().trim().isBlank())
        {
            throw new Exception("Tipo de cuenta vacia");
        }

        //Validación
        /*
        Optional<Cuenta> cuentaOptional = cuentaRepository.findCuentaByid(cuentaDTO.getId());
        if(cuentaOptional.isPresent()){
            throw new Exception(String.format("Ya esta registrado ese ID: %s", cuentaDTO.getId()));
        }
        */
        //Validar Si esa cuenta esta sujeta a un usuario Obvio no?
        Optional<Usuario> usuarioOptional = usuarioRepository.findUsuarioByid(cuentaDTO.getUsuarioId());
        if(usuarioOptional.isEmpty()){
            throw new Exception(String.format("No se puede registrar la cuenta si no tiene el ID usuario: %d",cuentaDTO.getUsuarioId()));

        }

        Cuenta cuenta = CuentaMapper.dtoToDomain(cuentaDTO);
        cuenta.setUsuario(usuarioOptional.get());

        cuenta = cuentaRepository.save(cuenta);

        return CuentaMapper.domainToDto(cuenta);

    }

    @Override
    public List<CuentaDTO> mostrarTodos() {
        return CuentaMapper.domainToDtoList(cuentaRepository.findAll());
    }

    //Desactivar cuenta
    @Override
    public ResponseEntity<Void> desactivarCuentaPorId(Integer cuentaId) {
        Optional<Cuenta> cuentaOptional = cuentaRepository.findCuentaById(cuentaId);

        if (cuentaOptional.isPresent()) {
            // Desactivar la cuenta
            Cuenta cuenta = cuentaOptional.get();
            cuenta.setActivo(false);
            cuentaRepository.save(cuenta);

            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity <Void> activarCuentaPorId(Integer cuentaId){
        Optional<Cuenta> cuentaOptional = cuentaRepository.findCuentaById(cuentaId);
        if (cuentaOptional.isPresent()) {
            // Desactivar la cuenta
            Cuenta cuenta = cuentaOptional.get();
            cuenta.setActivo(true);
            cuentaRepository.save(cuenta);

            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public List<TransaccionDTO> mostrarTransacciones(Integer cuentaId){
        Optional<Cuenta> cuentaOptional = cuentaRepository.findCuentaById(cuentaId);
        if (cuentaOptional.isPresent()){
            return TransaccionMapper.domainToDtoList(transaccionRepository.findTransaccionByCuentaId(cuentaId));
        }
        else{
            return null;
        }
    }
}
