package unide.usb.banco.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import unide.usb.banco.dto.UsuarioDTO;
import unide.usb.banco.repository.UsuarioRepository;
import unide.usb.banco.service.UsuarioService;

@Controller
public class HomeController {


    private UsuarioService usuarioService;

    // Constructor para inyección de dependencias
    public HomeController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @RequestMapping("/home")
    public String home(){
        return "inicio";
    }

    @RequestMapping(path = "/signup")
    public String sign(){
        return "sing-up";
    }

    @PostMapping("/singup")
    public String procesarFormulario(@ModelAttribute UsuarioDTO usuarioDTO, Model model) {
        try {
            // Llamar al servicio para guardar el usuario
            UsuarioDTO usuarioDTO1 = usuarioService.guardarNuevoUsuario(usuarioDTO);
            System.out.println(usuarioDTO1);
            // Redirigir a la página de inicio después de guardar el usuario
            return "redirect:/";
        } catch (Exception e) {
            // Manejar el error según sea necesario
            model.addAttribute("error", "Error al procesar el formulario");
            return "redirect:/signup";
        }
    }



    /*
    @RequestMapping(path = "/singup", method = RequestMethod.POST)
    public String crear(@ModelAttribute UsuarioDTO usuarioDTO, Model model) throws Exception {
        System.out.println(usuarioDTO);
        return "redirect:login";
    }
    */

    /*
    @GetMapping({"/", "/greeting"})
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "index";
    }

    @GetMapping({"/nose"})
    public String prueba(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "dos";
    }
    */
}
