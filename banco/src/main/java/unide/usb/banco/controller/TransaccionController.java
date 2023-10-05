package unide.usb.banco.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unide.usb.banco.domain.Transaccion;
import unide.usb.banco.dto.TransaccionDTO;
import unide.usb.banco.mapper.TransaccionMapper;
import unide.usb.banco.repository.TransaccionRepository;
import unide.usb.banco.service.TransaccionService;

import java.util.List;

@RestController
@RequestMapping("/Transaccion")
public class TransaccionController {

    private final TransaccionRepository transaccionRepository;
    private final TransaccionService transaccionService;


    public TransaccionController(TransaccionRepository transaccionRepository, TransaccionService transaccionService, TransaccionService transaccionService1) {
        this.transaccionRepository = transaccionRepository;
        this.transaccionService = transaccionService1;
    }

    @PostMapping("/Guardar")
    public ResponseEntity<TransaccionDTO> guardarTransaccion(@RequestBody TransaccionDTO transaccionDTO) throws Exception{
        TransaccionDTO transaccionDTO1 = transaccionService.guardarNuevoTransaccion(transaccionDTO);
        return new ResponseEntity<>(transaccionDTO1, HttpStatus.OK);
    }

    @GetMapping("/Registro")
    public List<Transaccion> obtenerTodos(){
        List<Transaccion> transaccions = transaccionRepository.findAll();
        return transaccions;
    }

    @GetMapping("/porID/{id}")
    public ResponseEntity<TransaccionDTO> buscarPorId(@PathVariable Integer id) throws Exception{
        Transaccion transaccion = transaccionRepository.getReferenceById(id);
        TransaccionDTO transaccionDTO = TransaccionMapper.domainToDto(transaccion);
        return new ResponseEntity<>(transaccionDTO, HttpStatus.OK);
    }
    @GetMapping("/buscarTodas")
    public ResponseEntity<List<TransaccionDTO>> buscarTodas(){
        return new ResponseEntity<>(transaccionService.buscarTodas(), HttpStatus.OK);
    }
}
