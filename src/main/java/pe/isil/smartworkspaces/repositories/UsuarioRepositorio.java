package pe.isil.smartworkspaces.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.isil.smartworkspaces.models.Usuario;

import java.util.Optional;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario,Integer> {
    Optional<Usuario> findByEmail(String email);

    Boolean existsByEmail(String email);
}
