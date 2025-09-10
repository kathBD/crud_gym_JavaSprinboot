package com.sena.crud_spring.repository;

import com.sena.crud_spring.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT u FROM Usuario u WHERE LOWER(u.rol.nombre) = LOWER(:nombreRol)")
    List<Usuario> findByRolNombreIgnoreCase(@Param("nombreRol") String nombreRol);

}

