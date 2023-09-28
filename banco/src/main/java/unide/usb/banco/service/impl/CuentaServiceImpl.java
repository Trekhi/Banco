package unide.usb.banco.service.impl;

import org.springframework.stereotype.Service;
import unide.usb.banco.domain.Cuenta;
import unide.usb.banco.dto.CuentaDTO;
import unide.usb.banco.mapper.CuentaMapper;
import unide.usb.banco.repository.CuentaRepository;
import unide.usb.banco.service.CuentaService;
@Service
public class CuentaServiceImpl implements CuentaService {

    private final CuentaRepository cuentaRepository;

    public CuentaServiceImpl(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    @Override
    public CuentaDTO guardarNuevaCuenta(CuentaDTO cuentaDTO) throws Exception {

        if (cuentaDTO == null)
        {
            throw new Exception("La cuenta es Nula");
        }

        if (cuentaDTO.getUsuario() == null || cuentaDTO.getUsuario().equals(""))
        {
            throw new Exception("No hay usuario registrado");
        }

        if (cuentaDTO.getFondos()== null || cuentaDTO.getFondos().equals(""))
        {
            throw new Exception("No tiene fondos registrados");
        }

        if (cuentaDTO.getTipocuenta() == null || cuentaDTO.getTipocuenta().trim().equals(""))
        {
            throw new Exception("Tipo de cuenta vacia");
        }

        if (cuentaDTO.getFechaapertura()== null || cuentaDTO.getFechaapertura().equals(""))
        {
            throw new Exception("Fecha de apertura vacia");
        }

        Cuenta cuenta = CuentaMapper.dtoToDomain(cuentaDTO);
        cuenta = cuentaRepository.save(cuenta);
        cuentaDTO = CuentaMapper.domainToDto(cuenta);

        return cuentaDTO;

    }
}
