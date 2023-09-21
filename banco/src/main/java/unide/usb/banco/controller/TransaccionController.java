package unide.usb.banco.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unide.usb.banco.domain.Transaccion;
import unide.usb.banco.domain.Usuario;
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
}
