package unide.usb.banco.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import unide.usb.banco.domain.Cuenta;
import unide.usb.banco.dto.CuentaDTO;
import unide.usb.banco.dto.TransaccionDTO;
import unide.usb.banco.dto.UsuarioDTO;
import unide.usb.banco.mapper.TransaccionMapper;
import unide.usb.banco.repository.UsuarioRepository;
import unide.usb.banco.service.CuentaService;
import unide.usb.banco.service.UsuarioService;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;


@Controller
public class HomeController {


    private UsuarioService usuarioService;
    private CuentaService cuentaService;

    // Constructor para inyección de dependencias
    public HomeController(UsuarioService usuarioService , CuentaService cuentaService) {
        this.usuarioService = usuarioService;
        this.cuentaService = cuentaService;
    }

    @RequestMapping("/home")
    public String home(){
        return "inicio";
    }

    @RequestMapping(path = "/signup")
    public String sign(){
        return "login";
    }

    /*CREAR USUARIO*/
    @PostMapping("/singup")
    public String procesarFormulario(@ModelAttribute UsuarioDTO usuarioDTO, Model model) {
        try {
            // Llamar al servicio para guardar el usuario
            UsuarioDTO usuarioDTO1 = usuarioService.guardarNuevoUsuario(usuarioDTO);
            System.out.println(usuarioDTO1);
            model.addAttribute("nombre", usuarioDTO1.getNombre());
            CuentaDTO cuentaDTO1 = new CuentaDTO( usuarioDTO.getId(), new BigDecimal("10000.00"), Instant.now(), "Cuenta Corriente", true);
            cuentaService.guardarNuevaCuenta(cuentaDTO1);
            model.addAttribute("cuentaDTO1", cuentaDTO1);
            return "login";
        } catch (Exception e) {
            // Manejar el error según sea necesario
            model.addAttribute("error", "Error al procesar el formulario");
            return "redirect:/signup";
        }
    }

    /*VERIFICAR USUARIO*/
    @PostMapping("/log")
    public String obtenerUsuarioPorCorreo(@RequestParam String correo, RedirectAttributes redirectAttributes, HttpSession session) {
        try {
            /*Para acceder ala cuenta*/
            UsuarioDTO usuarioDTO = usuarioService.obtenerPorCorreo(correo);
            CuentaDTO cuentaDTO = cuentaService.obtenerCuenta(usuarioDTO);
            List<TransaccionDTO> transacciones = cuentaService.mostrarTransacciones(cuentaDTO.getId());
            UsuarioDTO usuarioDTO1 = (UsuarioDTO) session.getAttribute("tempUsuario");
            redirectAttributes.addFlashAttribute("cuentaU", cuentaDTO);
            redirectAttributes.addFlashAttribute("nombre", usuarioDTO.getNombre());
            redirectAttributes.addFlashAttribute("transacciones", transacciones);
            session.setAttribute("tempUsuario", usuarioDTO);

            return "redirect:/ingreso";
        } catch (Exception e) {
            return "redirect:/home";
        }
    }

    @RequestMapping("/ingreso")
    public String ingreso(Model model){
        String nombreUsuario = (String) model.getAttribute("nombre");
        CuentaDTO cuentaDTO = (CuentaDTO) model.asMap().get("cuentaU");
        List<TransaccionDTO> transacciones = (List<TransaccionDTO>) model.asMap().get("transacciones");
        model.addAttribute("nombre", nombreUsuario);
        model.addAttribute("cuentaU", cuentaDTO );
        model.addAttribute("transacciones", transacciones);

        return "ingreso";
    }

    /*PARA ACTUALIZAR*/
    @RequestMapping("/actualizar")
    public String actualizar(){
        return "actualizar";
    }

    @PostMapping("/update")
    public String retornar(@ModelAttribute UsuarioDTO usuarioDTO, RedirectAttributes redirectAttributes, HttpSession session ) throws Exception {
        UsuarioDTO usuarioDTO1 = (UsuarioDTO) session.getAttribute("tempUsuario");
        System.out.println(usuarioDTO1); /*Ya puedo obtener los datos ahora solo falta */

        usuarioService.actualizarUsuario(1,usuarioDTO);
        session.removeAttribute("tempUsuario");
        return "redirect:/home";
    }


}
