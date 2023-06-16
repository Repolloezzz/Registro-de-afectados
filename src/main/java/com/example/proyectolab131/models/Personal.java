package com.example.proyectolab131.models;

import com.example.proyectolab131.others.EstadoPersonal;

import java.io.Serializable;

public class Personal implements Serializable {
    private int personalCi;
    private String cargo;
    private EstadoPersonal estado;

    public Personal(int personalCi) {
        this.personalCi = personalCi;
        this.cargo = "no definido";
        this.estado = EstadoPersonal.Libre;
    }

    public Personal(int personalCi, String cargo) {
        this.personalCi = personalCi;
        this.cargo = cargo;
        this.estado = EstadoPersonal.Libre;
    }

    public Personal(int personalCi, String cargo, EstadoPersonal estado) {
        this.personalCi = personalCi;
        this.cargo = cargo;
        this.estado = estado;
    }

    public int getPersonalCi() {
        return personalCi;
    }

    public void setPersonalCi(int personalCi) {
        this.personalCi = personalCi;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public EstadoPersonal getEstado() {
        return estado;
    }

    public void setEstado(EstadoPersonal estado) {
        this.estado = estado;
    }

    public void mostrar() {
        System.out.printf("""
                @CI: %s
                Cargo: %s \t Estado: %s
                """, personalCi, cargo, estado);
    }
}
