package unide.usb.banco.service.impl;

import org.springframework.stereotype.Service;
import unide.usb.banco.domain.Usuario;
import unide.usb.banco.dto.UsuarioDTO;
import unide.usb.banco.mapper.UsuarioMapper;
import unide.usb.banco.repository.UsuarioRepository;
import unide.usb.banco.service.UsuarioService;

import java.util.Optional;

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

        if (usuarioDTO.getNombre()== null || usuarioDTO.getNombre().trim().isBlank())
        {
            throw new Exception("No hay nombre");
        }
        if (usuarioDTO.getTelefono() == null || usuarioDTO.getTelefono().trim().isBlank())
        {
            throw new Exception("No hay no");
        }
        if (usuarioDTO.getDireccion() == null || usuarioDTO.getDireccion().trim().isBlank())
        {
            throw new Exception("No hay n");
        }
        if (usuarioDTO.getCorreo() == null || usuarioDTO.getCorreo().trim().isBlank())
        {
            throw new Exception("No hay ");
        }
        if (usuarioDTO.getContrasena()== null || usuarioDTO.getContrasena().trim().isBlank())
        {
            throw new Exception("Contraseña errada");
        }
        //validar que no haya repetidos EN ESTE CASO ESTO ES PARA PROBAR PERO NO SERIA NOMBRE SI NO CORREO.
        //Y TELEFONO Y DIRRECIÓN.findUsuarioBydirrecion(usuarioDTO.getDireccion());
        Optional<Usuario> usuarioPorCorreo = usuarioRepository.findUsuarioBycorreo(usuarioDTO.getCorreo());
        Optional<Usuario> usuarioPorDireccion = usuarioRepository.findUsuarioBydireccion(usuarioDTO.getDireccion());

        if(usuarioPorCorreo.isPresent()){
            throw new Exception(String.format("El correo ya se encuentra registrado %s",usuarioDTO.getCorreo()));
        }

        if(usuarioPorDireccion.isPresent()){
            throw new Exception(String.format("La dirección ya esta registrada %s",usuarioDTO.getDireccion()));
        }

        Usuario usuario = UsuarioMapper.dtoToDomain(usuarioDTO);
        usuario = usuarioRepository.save(usuario);

        return UsuarioMapper.dtoToDomain(usuario);



    }
}
