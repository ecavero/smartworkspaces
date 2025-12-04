package pe.isil.smartworkspaces.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.isil.smartworkspaces.models.Sala;
import pe.isil.smartworkspaces.repositories.SalaRepositorio;

@Controller
@RequestMapping("/admin/salas")
public class SalaControlador {

   @Autowired
   private SalaRepositorio salaRepositorio;


   @GetMapping("/")
   public String index(Model model) {
      List<Sala> salas = salaRepositorio.findAll();
      model.addAttribute("salas", salas);
      return "admin/salas";
   }

   @GetMapping("/nuevo")
   public String nuevaSala(Model model) {
      model.addAttribute("sala", new Sala());
      return "admin/nueva-sala";
   }

   @PostMapping("/guardar")
   public String guardarSala(Model model, Sala sala) {
      salaRepositorio.save(sala);
      return "redirect:/admin/salas/";
   }

   @GetMapping("/editar/{id}")
   public String editarSala(@PathVariable Integer id, Model model) {
      Sala sala = salaRepositorio.findById(id).orElseThrow(() -> new RuntimeException("Sala no encontrada"));
      model.addAttribute("sala", sala);
      return "admin/editar-sala";
   }
}
