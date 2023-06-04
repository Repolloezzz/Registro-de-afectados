package com.example.proyectolab131.model;

public class Desastre {
    private int id;
    private String nombre;
    private String descripcion;
    private String nivel_riesgo;

    public Desastre(int id, String nombre, String descripcion, String nivel_riesgo) {
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

    public String getNivel_riesgo() {
        return nivel_riesgo;
    }

    public void setNivel_riesgo(String nivel_riesgo) {
        this.nivel_riesgo = nivel_riesgo;
    }

    public void mostrar() {
        System.out.printf("""
                ID: %s
                Nombre: %s \t Nivel de riesgo: %s
                """, id, nombre, nivel_riesgo);
    }
}
