package unide.usb.banco.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unide.usb.banco.domain.Usuario;
import unide.usb.banco.repository.UsuarioRepository;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/validar")
    public String validarController(){
        return "Controlador Correcto";
    }

    @GetMapping("/Info")
    public List<Usuario> obtenerTodos(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios;
    }

    @GetMapping("/Object")
    public Usuario userObject(Usuario usuario) {
        usuario = usuarioRepository.save(usuario);
        return usuario;
    }

}
