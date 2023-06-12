package com.example.proyectolab131.models;

import com.example.proyectolab131.persistence.ArchFamilia;

public class Persona {
    private int ci;
    private String nombre;
    private int edad;
    private boolean genero;
    private boolean esVivo;

    private int familiaId;

    public Persona() {
    }


    public Persona(int ci, String nombre, int edad, boolean genero, boolean esVivo) {
        this.ci = ci;
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
        this.esVivo = esVivo;
        this.familiaId = -1;
    }

    public Persona(int ci, String nombre, int edad, boolean genero, boolean esVivo, int familiaId) {
        this.ci = ci;
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
        this.esVivo = esVivo;
        ArchFamilia arch = new ArchFamilia();
        Familia familia = arch.getFamilia(familiaId);
        if (familia != null) {
            this.familiaId = familiaId;
            familia.agregarFin(ci);
            arch.editarUno(familiaId, familia);
        } else {
            this.familiaId = -1;
            System.out.println("No existe la familiaID indicada en la persistencia");
        }
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

    public String getGenero() {
        // TRUE -> MASCULINO    || FALSE -> FEMENINO
        return (genero) ? "masculino" : "femenino";
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
        ArchFamilia arch = new ArchFamilia();
        Familia newFamilia = arch.getFamilia(familiaId);
        if (newFamilia != null) {
            Familia oldFamilia = getFamilia();
            if (oldFamilia != null) {
                oldFamilia.eliminarMiembro(ci);
            }
            newFamilia.agregarFin(ci);
            this.familiaId = familiaId;
        } else {
            System.out.println("No existe la familiaID en la persistencia");
        }
    }

    public void setFamiliaId(int familiaId, boolean forceProcess) {
        if (forceProcess) {
            this.familiaId = familiaId;
        } else {
            setFamiliaId(familiaId);
        }
    }

    public Familia getFamilia() {
        ArchFamilia arch = new ArchFamilia();
        Familia familia = null;
        if (familiaId != -1) {
            familia = arch.getFamilia(familiaId);
        }
        return familia;
    }

    public void mostrar() {
        System.out.printf("""
                CI: %s
                Nombre: %s
                Edad: %s \t Genero: %s
                Vivo?: %s \t FamiliaID: %s
                """, ci, nombre, edad, getGenero(), esVivo, familiaId);
    }
}
