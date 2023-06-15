package com.example.proyectolab131.models;

public class Persona {
    private Integer ci;
    private String nombres;
    private String apellidos;
    private Integer edad;
    private boolean genero;
    private boolean esVivo;

    private Integer familiaId;

    public Persona() {
    }


    public Persona(Integer ci, String nombres, String apellidos, Integer edad, boolean genero) {
        this.ci = ci;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.edad = edad;
        this.genero = genero;
        this.familiaId = -1;
    }

    public Persona(Integer ci, String nombres, String apellidos, Integer edad, boolean genero, boolean esVivo) {
        this.ci = ci;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.edad = edad;
        this.genero = genero;
        this.esVivo = esVivo;
        this.familiaId = -1;
    }

    public Persona(Integer ci, String nombres, String apellidos, Integer edad, boolean genero, boolean esVivo, Integer familiaId) {
        this.ci = ci;
        this.nombres = nombres;
        this.apellidos = apellidos;
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


    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombress) {
        this.nombres = nombress;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellido) {
        this.apellidos = apellido;
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

    public String getGenero() {
        // TRUE -> MASCULINO    || FALSE -> FEMENINO
        return (genero) ? "masculino" : "femenino";
    }

    public boolean esVivo() {
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


    public void mostrar() {
        System.out.printf("""
                CI: %s
                Nombres: %s \t Apellidos: %s
                Edad: %s \t Genero: %s
                Vivo?: %s \t FamiliaID: %s
                """, ci, nombres, apellidos, edad, getGenero(), esVivo, familiaId);
    }
}
