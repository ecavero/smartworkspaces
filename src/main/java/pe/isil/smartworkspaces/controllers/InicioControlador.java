package pe.isil.smartworkspaces.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.isil.smartworkspaces.models.Sala;
import pe.isil.smartworkspaces.repositories.SalaRepositorio;

@Controller
@RequestMapping("/")
public class InicioControlador {
   
   @Autowired
   SalaRepositorio salaRepositorio;
   @GetMapping("/")
   String index(Model model) {
      List<Sala> salas = salaRepositorio.findByEstado(true);
      model.addAttribute("salas", salas);
      return "index";
   }
}
