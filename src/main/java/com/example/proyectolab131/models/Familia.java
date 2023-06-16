package com.example.proyectolab131.models;

import com.example.proyectolab131.others.TipoMFamilia;
import com.example.proyectolab131.structures.LDNormal;
import com.example.proyectolab131.structures.NodoD;

import java.io.Serializable;
import java.util.function.Predicate;

public class Familia implements Serializable {
    // Identificador para la familia
    private int familiaId;
    private LDNormal<MiembroF> miembros;

    public Familia(int familiaId) {
        this.familiaId = familiaId;
        miembros = new LDNormal<>();
    }

    public Familia(int familiaId, int ci) {
        this.familiaId = familiaId;
        miembros = new LDNormal<>();
        agregarMiembro(ci, TipoMFamilia.Indefinido);
    }

    public Familia(int familiaId, int ci, TipoMFamilia rol) {
        this.familiaId = familiaId;
        miembros = new LDNormal<>();
        agregarMiembro(ci, rol);
    }

    public int getFamiliaId() {
        return familiaId;
    }

    public void setFamiliaId(int familiaId) {
        this.familiaId = familiaId;
    }

    public boolean contiene(int ci) {
        return miembros.contiene(ele -> ele.getCi() == ci);
    }

    public int nroMiembros() {
        return miembros.nroEle();
    }

    public int nroMiembros(Predicate<? super MiembroF> filtro) {
        return miembros.filter(filtro).nroEle();
    }

    public MiembroF getMiembro(int ci) {
        for (MiembroF ele : miembros) {
            if (ele.getCi() == ci) {
                return ele;
            }
        }
        return null;
    }

    public boolean agregarMiembro(int ci, TipoMFamilia rol) {
        boolean res = false;
        if (!contiene(ci)) {
            miembros.agregarFin(new MiembroF(ci, rol));
            res = true;
        } else {
            System.out.printf("El miembro ya existe; @ci: " + ci);
        }
        return res;
    }

    public MiembroF removerMiembro(int ci) {
        MiembroF res = null;
        NodoD<MiembroF> nodo = miembros.getP();
        int index = 0;
        while (nodo != null) {
            if (nodo.getDato().getCi() == ci) {
                res = nodo.getDato();
                break;
            }
            nodo = nodo.getSig();
            index++;
        }
        miembros.removerK(index);
        return res;
    }

    public LDNormal<Integer> getMiembrosCI() {
        LDNormal<Integer> list = new LDNormal<>();
        for (MiembroF ele : miembros) {
            list.agregarFin(ele.getCi());
        }
        return list;
    }

    public boolean editarMiembro(int ci, MiembroF data) {
        boolean res = false;
        if (contiene(ci)) {
            NodoD<MiembroF> nodo = miembros.getP();
            while (nodo != null) {
                if (nodo.getDato().getCi() == ci) {
                    nodo.setDato(data);
                    res = true;
                    break;
                }
                nodo = nodo.getSig();
            }
        } else {
            System.out.printf("El miembro no existe; @ci: " + ci);
        }
        return res;
    }

    public void mostrar() {
        System.out.println("@familiaId: " + familiaId);
        for (MiembroF ele : miembros) {
            ele.mostrar();
        }
        System.out.println("?? " + nroMiembros() + " Elementos listados");
    }

    public void mostrar(Predicate<? super MiembroF> filtro) {
        LDNormal<MiembroF> listFiltred = miembros.filter(filtro);
        System.out.println("@familiaId: " + familiaId);
        for (MiembroF ele : listFiltred) {
            ele.mostrar();
        }
        System.out.println("?? " + listFiltred.nroEle() + " Elementos listados");
    }
}
