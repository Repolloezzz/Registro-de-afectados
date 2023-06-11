package com.example.proyectolab131.models;

import com.example.proyectolab131.enums.TipoMiembroFamilia;
import com.example.proyectolab131.structures.LDNormal;

public class Familia extends LDNormal<MiembroF> {
    private int id;

    public Familia(int ci) {
        super();
        agregarFin(new MiembroF(ci));
    }

    public Familia(int ci, TipoMiembroFamilia tipo) {
        super();
        agregarFin(new MiembroF(ci, tipo));
    }

    public Familia(Persona miembro) {
        super();
        agregarFin(new MiembroF(miembro.getCi()));
    }

    public Familia(Persona miembro, TipoMiembroFamilia tipo) {
        super();
        agregarFin(new MiembroF(miembro.getCi(), tipo));
    }

    public void agregarFin(int ci) {
        agregarFin(new MiembroF(ci));
    }

    public void agregarFin(int ci, TipoMiembroFamilia tipo) {
        agregarFin(new MiembroF(ci, tipo));
    }

    public void agregarFin(Persona miembro) {
        agregarFin(new MiembroF(miembro.getCi()));
    }

    public void agregarFin(Persona miembro, TipoMiembroFamilia tipo) {
        agregarFin(new MiembroF(miembro.getCi(), tipo));
    }
    // Otros métodos
}
