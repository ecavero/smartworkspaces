package pe.isil.smartworkspaces.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.isil.smartworkspaces.models.Reserva;
import pe.isil.smartworkspaces.repositories.ReservaRepositorio;
import pe.isil.smartworkspaces.repositories.SalaRepositorio;

@Controller
@RequestMapping("/usuario/reservas")
public class ReservaControlador {

   @Autowired
   private ReservaRepositorio reservaRepositorio;
   @Autowired
   private SalaRepositorio salaRepositorio;

   @GetMapping("/")
   public String index(Model model) {
      List<Reserva> reservas = reservaRepositorio.findAll();
      model.addAttribute("reservas", reservas);
      return "usuario/reservas";
   }

   @GetMapping("/nuevo")
   public String nuevaReserva(Model model) {
      model.addAttribute("reserva", new Reserva());
      model.addAttribute("salas", salaRepositorio.findAll());
      return "usuario/nueva-reserva";
   }
}
