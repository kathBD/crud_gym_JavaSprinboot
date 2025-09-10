package com.sena.crud_spring.service;

import com.sena.crud_spring.model.Rol;
import com.sena.crud_spring.model.Usuario;
import com.sena.crud_spring.repository.IRolRepository;
import com.sena.crud_spring.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private IRolRepository rolRepository;

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }


    public List<Usuario> getUsuariosPorRol(String nombreRol) {
        return usuarioRepository.findByRolNombreIgnoreCase(nombreRol);
    }

    public Usuario guardarUsuario(Usuario usuario) {
        if (usuario.getRol() != null && usuario.getRol().getRolId() != null) {
            Rol rol = rolRepository.findById(usuario.getRol().getRolId())
                    .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
            usuario.setRol(rol);
        }
        return usuarioRepository.save(usuario);
    }
}




