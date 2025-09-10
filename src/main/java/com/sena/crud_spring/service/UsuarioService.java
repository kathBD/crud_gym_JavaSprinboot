package com.sena.crud_spring.service;

import com.sena.crud_spring.model.Rol;
import com.sena.crud_spring.model.Usuario;
import com.sena.crud_spring.repository.IRolRepository;
import com.sena.crud_spring.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio que maneja la lógica de negocio relacionada con los usuarios.
 * Esta clase se comunica con los repositorios para acceder a la base de datos.
 */

@Service
public class UsuarioService {
    /**
     * Repositorio para operaciones CRUD sobre la entidad Usuario.
     */
    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private IRolRepository rolRepository;

    /**
     * Obtiene la lista completa de usuarios registrados en el sistema.
     *
     * @return Lista de usuarios.
     */

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    /**
     * Obtiene todos los usuarios que tienen asignado un rol específico (por nombre).
     * El nombre del rol no es sensible a mayúsculas/minúsculas.
     *
     * @param nombreRol Nombre del rol.
     * @return Lista de usuarios filtrados por el rol especificado.
     */
    public List<Usuario> getUsuariosPorRol(String nombreRol) {
        return usuarioRepository.findByRolNombreIgnoreCase(nombreRol);
    }
    /**
     * Guarda un nuevo usuario en la base de datos, asegurándose de que el rol esté correctamente referenciado.
     * Si el rol existe, se asocia al usuario antes de guardar.
     *
     * @param usuario Objeto usuario a guardar.
     * @return Usuario guardado (con ID generado si corresponde).
     */
    public Usuario guardarUsuario(Usuario usuario) {
        if (usuario.getRol() != null && usuario.getRol().getRolId() != null) {
            Rol rol = rolRepository.findById(usuario.getRol().getRolId())
                    .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
            usuario.setRol(rol);
        }
        return usuarioRepository.save(usuario);
    }
}




