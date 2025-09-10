package com.sena.crud_spring.controller;

import com.sena.crud_spring.model.Usuario;
import com.sena.crud_spring.service.RolService;
import com.sena.crud_spring.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class VistaUsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolService rolService;

    @GetMapping("/usuarios")
    public String mostrarUsuarios(@RequestParam(value = "rol", required = false) String rol, Model model) {
        List<Usuario> usuarios;
        if (rol != null && !rol.isEmpty()) {
            usuarios = usuarioService.getUsuariosPorRol(rol);
            model.addAttribute("rolFiltrado", rol);
        } else {
            usuarios = usuarioService.getAllUsuarios();
        }
        model.addAttribute("usuarios", usuarios);
        return "usuarios";
    }

    @GetMapping("/usuarios/nuevo")
    public String mostrarFormularioUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("roles", rolService.getAllRoles());
        return "form_usuario";
    }

    @PostMapping("/usuarios/guardar")
    public String guardarUsuario(@ModelAttribute Usuario usuario) {
        if (usuario.getRol() != null && usuario.getRol().getRolId() != null) {
            usuario.setRol(rolService.getRolById(usuario.getRol().getRolId()));
        }
        usuarioService.guardarUsuario(usuario);
        return "redirect:/usuarios";
    }
}