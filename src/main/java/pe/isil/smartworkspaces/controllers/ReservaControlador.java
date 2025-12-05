package pe.isil.smartworkspaces.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.isil.smartworkspaces.models.Reserva;
import pe.isil.smartworkspaces.models.Sala;
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

   @PostMapping("/guardar")
   public String guardarReserva(Model model, Reserva reserva, RedirectAttributes ra) {
      String mensajePersonalizado = reserva.getId() == null ? "agregada" : "modificada";
      if (reserva.getSala() != null && reserva.getSala().getId() != null) {
         Sala sala = salaRepositorio.findById(reserva.getSala().getId())
            .orElseThrow(() -> new RuntimeException("Sala no encontrada"));
         reserva.setSala(sala);         
      }
      if (!reserva.isSalaActiva()) {
         throw new RuntimeException("Sala no activa");
      }
      reservaRepositorio.save(reserva);
      ra.addFlashAttribute("mensaje", String.format("La reserva fue %s con éxito", mensajePersonalizado));
      return "redirect:/usuario/reservas/";
   }

   @GetMapping("/editar/{id}")
   public String editarReserva(@PathVariable Integer id, Model model) {
      Reserva reserva = reservaRepositorio.findById(id)
         .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
      model.addAttribute("reserva", reserva);
      model.addAttribute("salas", salaRepositorio.findAll());
      return "usuario/editar-reserva";
   }

   
   @PostMapping("/eliminar/{id}")
   public String eliminarReserva(@PathVariable Integer id, RedirectAttributes ra) {
      reservaRepositorio.deleteById(id);
      ra.addFlashAttribute("mensaje", "La reserva fue eliminada con éxito");
      return "redirect:/usuario/reservas/";
   }
}
