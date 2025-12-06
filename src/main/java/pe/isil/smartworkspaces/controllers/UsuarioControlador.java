package pe.isil.smartworkspaces.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.isil.smartworkspaces.models.Usuario;
import pe.isil.smartworkspaces.repositories.UsuarioRepositorio;

@Controller
public class UsuarioControlador {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;


//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @GetMapping("/registro")
    public String index(Model model){
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    @PostMapping("/registro")
    public String registrar(@Validated Usuario usuario, BindingResult bindingResult, RedirectAttributes ra, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("usuario", usuario);
            return "registro";
        }

        //Validar si el email existe
        String email = usuario.getEmail();
        boolean existeUsuario = usuarioRepositorio.existsByEmail(email);

        if(existeUsuario){
            bindingResult.rejectValue("email", "EmailAlreadyExists");
        }

        //Validar si las contraseñas coinciden
        if (! usuario.getPassword1().equals(usuario.getPassword2())) {
            bindingResult.rejectValue("password1", "PasswordNotEquals");
        }

        if(bindingResult.hasErrors()){
            model.addAttribute("usuario", usuario);
            return "registro";
        }

        /*
        //Encriptar el password
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword1()));
        //usuario.setPassword(usuario.getPassword1());
        usuario.setRol(Usuario.Rol.ESTUDIANTE);

        //Guardamos en base de datos
        usuarioRepositorio.save(usuario);
        ra.addFlashAttribute("msgExito", "Usuario Registrado");
        */
        return "redirect:/login";

    }
}
