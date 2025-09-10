package com.sena.crud_spring.model;


import jakarta.persistence.*;

/**
 * Entidad que representa un usuario del sistema.
 * Esta clase se mapea a la tabla 'usuarios' en la base de datos.
 */

@Entity
@Table(name = "usuarios")
public class Usuario {

    /**
     * Identificador único del usuario.
     * Este campo es usado como clave primaria (PK) y no es autogenerado.
     */
    @Id
    @Column(name = "usuario_id")
    private String usuarioId;
    /**
     * Nombre del usuario.
     */
    private String nombre;
    @Column(name="correo", nullable = false, unique = true)

    /**
     * Correo electrónico del usuario.
     * Es obligatorio (nullable = false) y debe ser único en la base de datos.
     */
    private String correo;
    private String password;
    /**
     * Relación muchos-a-uno con la entidad Rol.
     * Cada usuario tiene asignado un único rol.
     */
    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;

    // Getters y Setters

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

}
