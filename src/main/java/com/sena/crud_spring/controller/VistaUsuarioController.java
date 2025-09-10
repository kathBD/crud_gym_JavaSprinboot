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

/**
 * Controlador encargado de manejar las vistas relacionadas con usuarios
 * utilizando Thymeleaf.
 */

@Controller
public class VistaUsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolService rolService;


    /**
     * Muestra la lista de usuarios. Si se proporciona un rol como parámetro,
     * se aplica un filtro para mostrar solo los usuarios con ese rol.
     *
     * @param rol   Nombre del rol para filtrar (opcional).
     * @param model Objeto Model para pasar datos a la vista.
     * @return Nombre de la plantilla Thymeleaf a renderizar.
     */
    @GetMapping("/usuarios")
    public String mostrarUsuarios(@RequestParam(value = "rol", required = false) String rol, Model model) {
        List<Usuario> usuarios;
        if (rol != null && !rol.isEmpty()) {
            // Filtra los usuarios por el nombre del rol ingresado
            usuarios = usuarioService.getUsuariosPorRol(rol);
            model.addAttribute("rolFiltrado", rol);
        } else {
            usuarios = usuarioService.getAllUsuarios();
        }
        model.addAttribute("usuarios", usuarios);
        return "usuarios"; // Retorna la vista usuarios.html
    }
    /**
     * Muestra el formulario para registrar un nuevo usuario.
     *
     * @param model Modelo para pasar el objeto usuario y lista de roles a la vista.
     * @return Nombre de la vista del formulario (form_usuario.html).
     */
    @GetMapping("/usuarios/nuevo")
    public String mostrarFormularioUsuario(Model model) {
        model.addAttribute("usuario", new Usuario()); // Objeto vacío para el formulario
        model.addAttribute("roles", rolService.getAllRoles()); // Lista de roles para el combo/select
        return "registro_usuario";
    }

    /**
     * Guarda un nuevo usuario enviado desde el formulario.
     * También resuelve el objeto Rol completo antes de guardarlo.
     *
     * @param usuario Objeto Usuario con los datos del formulario.
     * @return Redirección a la lista de usuarios.
     */
    @PostMapping("/usuarios/guardar")
    public String guardarUsuario(@ModelAttribute Usuario usuario) {
        if (usuario.getRol() != null && usuario.getRol().getRolId() != null) {
            usuario.setRol(rolService.getRolById(usuario.getRol().getRolId()));
        }
        usuarioService.guardarUsuario(usuario); // Guarda el usuario
        return "redirect:/usuarios"; // Redirige a la vista de lista
    }
}