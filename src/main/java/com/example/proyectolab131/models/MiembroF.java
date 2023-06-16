package com.example.proyectolab131.models;

import com.example.proyectolab131.others.TipoMFamilia;

import java.io.Serializable;

public class MiembroF implements Serializable {
    // Enlace de la familia con una persona
    private int ci;
    // Rol familiar que cumple la persona en la familia
    private TipoMFamilia rol;

    MiembroF(int ci) {
        this.ci = ci;
        this.rol = TipoMFamilia.Indefinido;
    }

    public MiembroF(int ci, TipoMFamilia rol) {
        this.ci = ci;
        this.rol = rol;
    }

    public int getCi() {
        return ci;
    }

    public void setCi(int ci) {
        this.ci = ci;
    }

    public TipoMFamilia getRol() {
        return rol;
    }

    public void setRol(TipoMFamilia rol) {
        this.rol = rol;
    }

    public void mostrar() {
        System.out.printf("""
                @CI: %s \t Rol: %s
                """, ci, rol);
    }
}
