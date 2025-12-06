package pe.isil.smartworkspaces.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.isil.smartworkspaces.models.Usuario;
import pe.isil.smartworkspaces.repositories.UsuarioRepositorio;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.findByEmail(username)
                .orElseThrow(()-> new UsernameNotFoundException("Usuario no encontrado" + username));

        return  new AppUserDetails(usuario);
    }
}
