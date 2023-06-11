package com.example.proyectolab131.models;

import com.example.proyectolab131.enums.TipoMiembroFamilia;

// Miembro familiar
public class MiembroF {
    private TipoMiembroFamilia tipo;
    private int miembroCI;

    public MiembroF(int miembroCI, TipoMiembroFamilia tipo) {
        this.tipo = tipo;
        this.miembroCI = miembroCI;
    }

    public MiembroF(int miembroCI) {
        this.miembroCI = miembroCI;
        this.tipo = TipoMiembroFamilia.Indefinido;
    }

    public TipoMiembroFamilia getTipo() {
        return tipo;
    }

    public void setTipo(TipoMiembroFamilia tipo) {
        this.tipo = tipo;
    }

    public int getMiembroCI() {
        return miembroCI;
    }

    public void setMiembroCI(int miembroCI) {
        this.miembroCI = miembroCI;
    }
}
