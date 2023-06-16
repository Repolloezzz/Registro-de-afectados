package com.example.proyectolab131.models;

import com.example.proyectolab131.others.NivelRiesgo;

public class FenomenoN {
    private int id;
    private String nombre;
    private String descripcion;
    private NivelRiesgo nivel_riesgo;

    public FenomenoN(int id, String nombre, String descripcion, NivelRiesgo nivel_riesgo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.nivel_riesgo = nivel_riesgo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public NivelRiesgo getNivel_riesgo() {
        return nivel_riesgo;
    }

    public void setNivel_riesgo(NivelRiesgo nivel_riesgo) {
        this.nivel_riesgo = nivel_riesgo;
    }

    public void mostrar() {
        System.out.printf("""
                @id: %s
                Nombre: %s \t Nivel de riesgo: %s
                """, id, nombre, nivel_riesgo);
    }
}
