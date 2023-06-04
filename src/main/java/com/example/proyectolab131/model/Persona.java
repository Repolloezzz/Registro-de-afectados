package com.example.proyectolab131.model;

public class Persona {
    private int ci;
    private String nombre;
    private int edad;
    private boolean genero;
    private boolean esVivo;

    public Persona(int ci, String nombre, int edad, boolean genero) {
        this.ci = ci;
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
        this.esVivo = true;
    }

    public Persona(int ci, String nombre, int edad, boolean genero, boolean esVivo) {
        this.ci = ci;
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
        this.esVivo = esVivo;
    }

    public int getCi() {
        return ci;
    }

    public void setCi(int ci) {
        this.ci = ci;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public boolean isGenero() {
        return genero;
    }

    public void setGenero(boolean genero) {
        this.genero = genero;
    }

    public boolean esVivo() {
        return esVivo;
    }

    public void setEsVivo(boolean esVivo) {
        this.esVivo = esVivo;
    }

    public String getGenero() {
        return (genero) ? "Masculino" : "Femenino";
    }

    public String getEstado() {
        return (esVivo) ? "Vivo" : "Muerto";
    }

    public void mostrar() {
        System.out.printf("""
                CI: %s
                Nombre: %s,
                Edad: %s \t Genero: %s,
                Estado: %s
                """, ci, nombre, edad, getGenero(), getEstado());
    }
}
