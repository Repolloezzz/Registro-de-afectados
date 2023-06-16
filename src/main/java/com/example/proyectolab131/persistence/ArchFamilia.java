package com.example.proyectolab131.persistence;

import com.example.proyectolab131.models.Familia;
import com.example.proyectolab131.models.MiembroF;
import com.example.proyectolab131.others.TipoMFamilia;
import com.example.proyectolab131.structures.LDNormal;
import com.example.proyectolab131.structures.NodoD;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ArchFamilia {
    private String filePath;
    private LDNormal<Familia> conjunto;

    public ArchFamilia() {
        this.filePath = "data/familias.obj";
        this.conjunto = new LDNormal<>();
        cargarDatos();
    }

    public ArchFamilia(String filePath) {
        this.filePath = filePath;
        this.conjunto = new LDNormal<>();
        cargarDatos();
    }

    public void cargarDatos() {
        try {
            FileInputStream fileIn = new FileInputStream(filePath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            List<Familia> listRes = (List<Familia>) objectIn.readObject();
            objectIn.close();
            fileIn.close();
            for (Familia ele : listRes) {
                this.conjunto.agregarFin(ele);
            }
            System.out.println("Los datos se han cargado correctamente desde: " + filePath);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar los datos: " + e.getMessage());
        }
    }

    public void guardarDatos() {
        try {
            FileOutputStream fileOut = new FileOutputStream(filePath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            List<Familia> listOut = new ArrayList<>();
            for (Familia ele : this.conjunto) {
                listOut.add(ele);
            }
            objectOut.writeObject(listOut);
            objectOut.close();
            fileOut.close();
            System.out.println("Los datos se han guardado correctamente: " + filePath);
        } catch (IOException e) {
            System.out.println("Error al guardar los datos: " + e.getMessage());
        }
    }

    public LDNormal<Familia> getConjunto() {
        return this.conjunto;
    }

    public LDNormal<Familia> getConjunto(Predicate<? super Familia> filtro) {
        return this.conjunto.filter(filtro);
    }

    public int nroEle() {
        return this.conjunto.nroEle();
    }

    public int nroEle(Predicate<? super Familia> filtro) {
        return this.conjunto.filter(filtro).nroEle();
    }

    public boolean contiene(int familiaId) {
        return this.conjunto.contiene(ele -> ele.getFamiliaId() == familiaId);
    }

    public boolean agregar(Familia data) {
        boolean res = false;
        if (!contiene(data.getFamiliaId())) {
            this.conjunto.agregarFin(data);
            guardarDatos();
            res = true;
        } else {
            System.out.printf("!! La familia ya existe @familiaId: " + data.getFamiliaId());
        }
        return res;
    }

    public boolean editar(int familiaId, Familia data) {
        boolean res = false;
        if (contiene(familiaId)) {
            NodoD<Familia> nodo = this.conjunto.getP();
            while (nodo != null) {
                if (nodo.getDato().getFamiliaId() == familiaId) {
                    nodo.setDato(data);
                    guardarDatos();
                    res = true;
                    break;
                }
                nodo = nodo.getSig();
            }
        } else {
            System.out.printf("!! La familia no existe @familiaId: " + familiaId);
        }
        return res;
    }

    public Familia borrar(int familiaId) {
        Familia res = null;
        NodoD<Familia> nodo = this.conjunto.getP();
        int index = 0;
        while (nodo != null) {
            if (nodo.getDato().getFamiliaId() == familiaId) {
                res = nodo.getDato();
                break;
            }
            nodo = nodo.getSig();
            index++;
        }
        this.conjunto.removerK(index);
        guardarDatos();
        return res;
    }

    public Familia getFamilia(int familiaId) {
        Familia res = null;
        NodoD<Familia> nodo = this.conjunto.getP();
        while (nodo != null) {
            if (nodo.getDato().getFamiliaId() == familiaId) {
                res = nodo.getDato();
                break;
            }
            nodo = nodo.getSig();
        }
        return res;
    }

    public void listar() {
        System.out.printf("Elementos del archivo");
        System.out.println();
        for (Familia ele : this.conjunto) {
            ele.mostrar();
        }
        System.out.println("?? " + nroEle() + " Elmentos listados");
    }

    public void listar(Predicate<? super Familia> filtro) {
        LDNormal<Familia> listFiltred = this.conjunto.filter(filtro);
        System.out.printf("Elementos filtrados del archivo " + filtro.toString());
        System.out.println();
        for (Familia ele : listFiltred) {
            ele.mostrar();
        }
        System.out.println("?? " + listFiltred.nroEle() + " Elmentos listados");
    }

    public boolean agregarMiembro(int familiaId, int ci, TipoMFamilia rol) {
        boolean res = false;
        if (contiene(familiaId)) {
            Familia ele = getFamilia(familiaId);
            res = ele.agregarMiembro(ci, rol);
            guardarDatos();
        } else {
            System.out.printf("!! La familia no existe @familiaId: " + familiaId);
        }
        return res;
    }

    public MiembroF removerMiembro(int familiaId, int ci) {
        MiembroF res = null;
        if (contiene(familiaId)) {
            Familia ele = getFamilia(familiaId);
            res = ele.removerMiembro(ci);
            guardarDatos();
        } else {
            System.out.printf("!! La familia no existe @familiaId: " + familiaId);
        }
        return res;
    }

    public void limpiar(boolean confirm1, boolean confirm2) {
        if (confirm1 && confirm2) {
            this.conjunto = new LDNormal<>();
            guardarDatos();
        }
    }
}
