package pe.isil.smartworkspaces.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Entity
@Data
public class Sala {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;
   @Column(unique = true)
   private String nombre;
   @Min(value = 1, message = "La capacidad debe ser mayor a cero")
   private Integer capacidad;
   private Boolean estado;

   @PrePersist
   void asignarEstado() {
      estado = true;
   }

}
