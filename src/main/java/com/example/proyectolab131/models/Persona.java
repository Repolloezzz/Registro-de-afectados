package com.example.proyectolab131.models;

import java.io.Serializable;

public class Persona implements Serializable {
    private int ci;
    private String nombres;
    private String apellidos;
    private int edad;
    // 👨 TRUE = HOMBRE || 👩 FALSE = MUJER
    private boolean genero;
    // 💖 TRUE = VIVO || 💀 FALSE = MUERTO
    private boolean esVivo;
    // Enlace con su familia [ -1 ] = NO TIENE FAMILIA
    private int familiaId;

    public Persona(int ci, String nombres, String apellidos, int edad, boolean genero) {
        this.ci = ci;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.edad = edad;
        this.genero = genero;
        this.esVivo = true;
        this.familiaId = -1;
    }

    public Persona(int ci, String nombres, String apellidos, int edad, boolean genero, boolean esVivo) {
        this.ci = ci;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.edad = edad;
        this.genero = genero;
        this.esVivo = esVivo;
        this.familiaId = -1;
    }

    public Persona(int ci, String nombres, String apellidos, int edad, boolean genero, boolean esVivo, int familiaId) {
        this.ci = ci;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.edad = edad;
        this.genero = genero;
        this.esVivo = esVivo;
        this.familiaId = familiaId;
    }

    public int getCi() {
        return ci;
    }

    public void setCi(int ci) {
        this.ci = ci;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
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

    public boolean isEsVivo() {
        return esVivo;
    }

    public void setEsVivo(boolean esVivo) {
        this.esVivo = esVivo;
    }

    public int getFamiliaId() {
        return familiaId;
    }

    public void setFamiliaId(int familiaId) {
        this.familiaId = familiaId;
    }

    public void mostrar() {
        System.out.printf("""
                @CI : %s
                Nombres: %s \t Apellidos: %s
                Edad: %s \t Genero: %s
                Vivo? %s \t @FamiliaId: %s
                """, ci, nombres, apellidos, edad, genero, esVivo, familiaId);
    }
}
