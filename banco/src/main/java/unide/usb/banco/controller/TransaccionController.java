package unide.usb.banco.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unide.usb.banco.domain.Transaccion;
import unide.usb.banco.domain.Usuario;
import unide.usb.banco.dto.TransaccionDTO;
import unide.usb.banco.mapper.TransaccionMapper;
import unide.usb.banco.repository.TransaccionRepository;

import java.util.List;

@RestController
@RequestMapping("/Transaccion")
public class TransaccionController {

    private final TransaccionRepository transaccionRepository;

    public TransaccionController(TransaccionRepository transaccionRepository) {this.transaccionRepository = transaccionRepository;}

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
}
