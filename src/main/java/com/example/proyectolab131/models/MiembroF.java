package com.example.proyectolab131.models;

import com.example.proyectolab131.enums.TipoMFamilia;

public class MiembroF {
    private Integer miembroCi;
    private TipoMFamilia tipo;

    public MiembroF(Integer miembroCi) {
        this.miembroCi = miembroCi;
        this.tipo = TipoMFamilia.Indefinido;
    }

    public MiembroF(Integer miembroCi, TipoMFamilia tipo) {
        this.miembroCi = miembroCi;
        this.tipo = tipo;
    }

    public Integer getMiembroCi() {
        return miembroCi;
    }

    public void setMiembroCi(Integer miembroCi) {
        this.miembroCi = miembroCi;
    }

    public TipoMFamilia getTipo() {
        return tipo;
    }

    public void setTipo(TipoMFamilia tipo) {
        this.tipo = tipo;
    }

    public void mostrar() {
        System.out.printf("""
                MiembroCI: %s
                Rol Familiar: %s
                """, miembroCi, tipo);
    }
}
