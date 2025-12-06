package pe.isil.smartworkspaces.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.isil.smartworkspaces.models.Sala;

public interface SalaRepositorio extends JpaRepository<Sala, Integer> {
    boolean existsByNombre(String nombre);
    List<Sala> findByEstado(Boolean estado);
}
