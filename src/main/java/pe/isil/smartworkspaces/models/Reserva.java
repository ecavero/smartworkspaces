package pe.isil.smartworkspaces.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Reserva {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;
   @NotBlank(message = "El usuario es obligatorio")
   private String usuario;
   @NotNull(message = "La fecha es obligatoria")
   @Future(message = "La fecha debe ser futura")
   private LocalDate fecha;
   @NotNull(message = "La hora de inicio es obligatoria")
   private LocalTime horaInicio;
   @NotNull(message = "La hora de fin es obligatoria")
   private LocalTime horaFin;
   @ManyToOne
   @JoinColumn(name = "salaId")
   @NotNull(message = "La sala es obligatoria")
   private Sala sala;
   
   public boolean isSalaActiva() {
      return sala != null && sala.getEstado();
   }

   public String getFechaEditar() {
      return fecha != null ? fecha.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : null;
   }

   public void setFechaEditar(String fechaEditar) {
      if (fechaEditar != null && !fechaEditar.isEmpty()) {
         this.fecha = LocalDate.parse(fechaEditar, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
      } else {
         this.fecha = null;
      }
   }
}
