package com.example.proyectolab131.models;

import com.example.proyectolab131.enums.TipoMFamilia;
import com.example.proyectolab131.structures.LDNormal;


public class Familia extends LDNormal<MiembroF> {
    private Integer familiaId;

    public Familia(Integer familiaId) {
        super();
        this.familiaId = familiaId;
    }

    public Familia(Integer miembroCi, Integer familiaId) {
        super();
        this.familiaId = familiaId;
        agregarFin(new MiembroF(miembroCi));
    }

    public Familia(Integer miembroCi, TipoMFamilia tipo, Integer familiaId) {
        super();
        this.familiaId = familiaId;
        agregarFin(new MiembroF(miembroCi, tipo));
    }

    public Familia(MiembroF miembro, Integer familiaId) {
        super();
        this.familiaId = familiaId;
        agregarFin(miembro);
    }

    public Integer getFamiliaId() {
        return familiaId;
    }

    public void setFamiliaId(Integer familiaId) {
        this.familiaId = familiaId;
    }

    public MiembroF getMiembro(Integer miembroCi) {
        MiembroF res = null;
        for (int i = 0; i < nroEle; i++) {
            MiembroF ele = getK(i);
            if (ele.getMiembroCi().equals(miembroCi)) {
                res = ele;
                break;
            }
        }
        return res;
    }

    public MiembroF removeMiembro(Integer miembroCi) {
        MiembroF res = null;
        for (int i = 0; i < nroEle; i++) {
            MiembroF ele = getK(i);
            if (ele.getMiembroCi().equals(miembroCi)) {
                res = removerK(i);
                nroEle--;
                break;
            }
        }
        return res;
    }

    public void agregarFin(Integer ci, TipoMFamilia tipo) {
        if (getMiembro(ci) == null) {
            agregarFin(new MiembroF(ci, tipo));
        } else {
            System.out.println("El miembro ya existe");
        }
    }

    public void agregarFin(Integer ci) {
        agregarFin(ci, TipoMFamilia.Indefinido);
    }

    public LDNormal<Integer> getListMiembrosCi() {
        LDNormal<Integer> listRes = new LDNormal<>();
        for (MiembroF ele : this) {
            listRes.agregarFin(ele.getMiembroCi());
        }
        return listRes;
    }

    public void mostrar() {
        System.out.println("@Familia: " + familiaId);
        System.out.println();
        for (MiembroF ele : this) {
            ele.mostrar();
        }
        System.out.println(nroEle + " elementos listados");
    }
}
