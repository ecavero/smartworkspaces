package pe.isil.smartworkspaces.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.isil.smartworkspaces.models.Reserva;

public interface ReservaRepositorio extends JpaRepository<Reserva, Integer> {
}
