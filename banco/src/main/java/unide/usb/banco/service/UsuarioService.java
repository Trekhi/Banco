package unide.usb.banco.service;

import unide.usb.banco.dto.UsuarioDTO;

public interface UsuarioService {

    public UsuarioDTO guardarNuevoUsuario(UsuarioDTO usuarioDTO) throws Exception;
}
