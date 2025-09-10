package com.sena.crud_spring.repository;

import com.sena.crud_spring.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repositorio para la entidad Usuario.
 * Hereda de JpaRepository para aprovechar métodos CRUD ya definidos.
 */

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
    /**
     * Busca usuarios filtrados por el nombre del rol asociado.
     *
     * Se realiza una comparación en minúsculas (case-insensitive)
     * para asegurar que el filtro no dependa de mayúsculas/minúsculas.
     *
     * @param nombreRol El nombre del rol a filtrar (por ejemplo: "administrador").
     * @return Lista de usuarios cuyo rol coincida con el nombre proporcionado.
     */
    @Query("SELECT u FROM Usuario u WHERE LOWER(u.rol.nombre) = LOWER(:nombreRol)")
    List<Usuario> findByRolNombreIgnoreCase(@Param("nombreRol") String nombreRol);

}

