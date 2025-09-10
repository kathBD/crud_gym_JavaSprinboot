package com.sena.crud_spring.model;


import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name="rutinas")
public class Rutina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rutina_id")
    private  Integer rutinaId;

    private String nombre;
    private String descripcion;
    @OneToMany(mappedBy = "rutina", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EjercicioRutina> ejercicios;

    // Getters y Setters

    public Integer getRutinaId() {
        return rutinaId;
    }

    public void setRutinaId(Integer rutinaId) {
        this.rutinaId = rutinaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<EjercicioRutina> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(List<EjercicioRutina> ejercicios) {
        this.ejercicios = ejercicios;
    }

}
