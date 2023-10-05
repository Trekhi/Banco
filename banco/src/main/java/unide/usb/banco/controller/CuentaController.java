package unide.usb.banco.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unide.usb.banco.domain.Cuenta;
import unide.usb.banco.dto.CuentaDTO;
import unide.usb.banco.mapper.CuentaMapper;
import unide.usb.banco.repository.CuentaRepository;
import unide.usb.banco.service.CuentaService;

import java.util.List;

@RestController
@RequestMapping("/Cuenta")
public class CuentaController {

    private final CuentaRepository cuentaRepository;
    private final CuentaService cuentaService;


    public CuentaController(CuentaRepository cuentaRepository, CuentaService cuentaService) {
        this.cuentaRepository = cuentaRepository;
        this.cuentaService = cuentaService;
    }

    @PostMapping("/Guardar")
    public ResponseEntity<CuentaDTO> guardarCuenta(@RequestBody CuentaDTO cuentaDTO) throws Exception{
        CuentaDTO cuentaDTO1 = cuentaService.guardarNuevaCuenta(cuentaDTO);
        return new ResponseEntity<>(cuentaDTO1, HttpStatus.OK);
    }

    @GetMapping("/Mostrar")
    public ResponseEntity<List<CuentaDTO>> mostrarTodos(){
        return new ResponseEntity<>(cuentaService.mostrarTodos(),HttpStatus.OK);
    }

    @GetMapping("/Informe")
    public List<Cuenta> obtenerTodos(){
        List<Cuenta> cuentas = cuentaRepository.findAll();
        return cuentas;
    }

    @GetMapping("/porID/{id}")
    public ResponseEntity<CuentaDTO> buscarPorId(@PathVariable Integer id) throws Exception{
        Cuenta cuenta = cuentaRepository.getReferenceById(id);
        CuentaDTO cuentaDTO = CuentaMapper.domainToDto(cuenta);
        return new ResponseEntity<>(cuentaDTO, HttpStatus.OK);
    }
}
