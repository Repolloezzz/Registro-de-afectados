package com.example.proyectolab131.models;

public class Persona {
    private Integer ci;
    private String nombre;
    private Integer edad;
    private boolean genero;
    private boolean esVivo;

    private Integer familiaId;

    public Persona() {
    }

    public Persona(Integer ci, String nombre, Integer edad, boolean genero, boolean esVivo, Integer familiaId) {
        this.ci = ci;
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
        this.esVivo = esVivo;
        this.familiaId = familiaId;
    }

    public Integer getCi() {
        return ci;
    }

    public void setCi(Integer ci) {
        this.ci = ci;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public boolean isGenero() {
        return genero;
    }

    public void setGenero(boolean genero) {
        this.genero = genero;
    }

    public boolean isEsVivo() {
        return esVivo;
    }

    public void setEsVivo(boolean esVivo) {
        this.esVivo = esVivo;
    }

    public Integer getFamiliaId() {
        return familiaId;
    }

    public void setFamiliaId(Integer familiaId) {
        this.familiaId = familiaId;
    }
}
