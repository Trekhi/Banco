package unide.usb.banco.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unide.usb.banco.domain.Usuario;
import unide.usb.banco.dto.UsuarioDTO;
import unide.usb.banco.mapper.UsuarioMapper;
import unide.usb.banco.repository.UsuarioRepository;
import unide.usb.banco.service.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioRepository usuarioRepository, UsuarioService usuarioService) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/Guardar")
    public ResponseEntity<UsuarioDTO> guardarUsuario(UsuarioDTO usuarioDTO) throws Exception{
        UsuarioDTO usuarioDTO1 = usuarioService.guardarNuevoUsuario(usuarioDTO);
        return new ResponseEntity<>(usuarioDTO1, HttpStatus.OK);
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

    @GetMapping("/porID/{id}")
    public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable Integer id) throws Exception {
        Usuario usuario = usuarioRepository.getReferenceById(id);
        UsuarioDTO usuarioDTO = UsuarioMapper.dtoToDomain(usuario);
        return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
    }

}
