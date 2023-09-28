package unide.usb.banco.service.impl;

import org.springframework.stereotype.Service;
import unide.usb.banco.domain.Usuario;
import unide.usb.banco.dto.UsuarioDTO;
import unide.usb.banco.mapper.UsuarioMapper;
import unide.usb.banco.repository.UsuarioRepository;
import unide.usb.banco.service.UsuarioService;
@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UsuarioDTO guardarNuevoUsuario(UsuarioDTO usuarioDTO) throws Exception {

        if (usuarioDTO == null)
        {
            throw new Exception("El usuario es Nulo");
        }

        if (usuarioDTO.getNombre()== null || usuarioDTO.getNombre().trim().equals(""))
        {
            throw new Exception("No hay nombre");
        }
        if (usuarioDTO.getTelefono() == null || usuarioDTO.getTelefono().trim().equals(""))
        {
            throw new Exception("No hay no");
        }
        if (usuarioDTO.getDireccion() == null || usuarioDTO.getDireccion().trim().equals(""))
        {
            throw new Exception("No hay n");
        }
        if (usuarioDTO.getCorreo() == null || usuarioDTO.getCorreo().trim().equals(""))
        {
            throw new Exception("No hay ");
        }
        if (usuarioDTO.getContrasena()== null || usuarioDTO.getContrasena().trim().equals(""))
        {
            throw new Exception("Contraseña errada");
        }

        Usuario usuario = UsuarioMapper.dtoToDomain(usuarioDTO);
        usuario = usuarioRepository.save(usuario);
        usuarioDTO = UsuarioMapper.dtoToDomain(usuario);

        return usuarioDTO;



    }
}
