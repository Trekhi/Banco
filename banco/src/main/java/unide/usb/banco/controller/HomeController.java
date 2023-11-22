package unide.usb.banco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {


    @RequestMapping("/home")
    public String home(){
        System.out.println("Prueba");
        return "inicio";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String envio(Model model){

        return "login";
    }
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
