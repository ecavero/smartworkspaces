package pe.isil.smartworkspaces.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class InicioControlador {
   String index(Model model) {
      return "index";
   }
}
