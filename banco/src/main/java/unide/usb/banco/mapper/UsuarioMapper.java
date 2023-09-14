package unide.usb.banco.mapper;


import unide.usb.banco.domain.Usuario;
import unide.usb.banco.dto.UsuarioDTO;

import java.util.List;


public class UsuarioMapper {

    public static Usuario dtoToDomain (UsuarioDTO usuarioDTO) {
        return Usuario.builder()
                .id(usuarioDTO.getId())
                .nombre(usuarioDTO.getNombre())
                .telefono(usuarioDTO.getTelefono())
                .dirrecion(usuarioDTO.getDirrecion())
                .correo(usuarioDTO.getCorreo())
                .contrasena(usuarioDTO.getContrasena())
                .build();
    }

    public static UsuarioDTO dtoToDomain (Usuario usuario){
        return UsuarioDTO.builder()
                .id(usuario.getId())
                .nombre(usuario.getNombre())
                .telefono(usuario.getTelefono())
                .dirrecion(usuario.getDirrecion())
                .correo(usuario.getCorreo())
                .contrasena(usuario.getContrasena())
                .build();
    }

    public static List<Usuario> dtoToDomainList (List<UsuarioDTO> usuarioDTOS){
        return usuarioDTOS.stream().map(UsuarioMapper::dtoToDomain).toList();
    }

    public static  List<UsuarioDTO> domainToDtolist (List<Usuario> usuarios){
        return usuarios.stream().map((UsuarioMapper::dtoToDomain)).toList();
    }

}
