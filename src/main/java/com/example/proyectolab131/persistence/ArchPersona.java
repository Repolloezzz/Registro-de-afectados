package com.example.proyectolab131.persistence;

import com.example.proyectolab131.models.Persona;
import com.example.proyectolab131.structures.LDNormal;
import com.example.proyectolab131.structures.NodoD;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ArchPersona {
    // 📁 directorio del archivo de persistencia
    private String filePath;
    // 📚 elementos persistentes del archivo
    private LDNormal<Persona> conjunto;

    public ArchPersona() {
        this.filePath = "data/personas.obj";
        this.conjunto = new LDNormal<>();
        cargarDatos();
    }

    public ArchPersona(String filePath) {
        this.filePath = filePath;
        this.conjunto = new LDNormal<>();
        cargarDatos();
    }

    public void cargarDatos() {
        try {
            FileInputStream fileIn = new FileInputStream(filePath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            List<Persona> listRes = (List<Persona>) objectIn.readObject();
            objectIn.close();
            fileIn.close();
            for (Persona ele : listRes) {
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
            List<Persona> listOut = new ArrayList<>();
            for (Persona ele : conjunto) {
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

    public LDNormal<Persona> getConjunto() {
        return this.conjunto;
    }

    public LDNormal<Persona> getConjunto(Predicate<? super Persona> filtro) {
        return this.conjunto.filter(filtro);
    }

    public int nroEle() {
        return this.conjunto.nroEle();
    }

    public int nroEle(Predicate<? super Persona> filtro) {
        return this.conjunto.filter(filtro).nroEle();
    }

    public boolean contiene(int ci) {
        return this.conjunto.contiene(ele -> ele.getCi() == ci);
    }

    public boolean agregar(Persona data) {
        boolean res = false;
        if (!contiene(data.getCi())) {
            this.conjunto.agregarFin(data);
            guardarDatos();
            res = true;
        } else {
            System.out.printf("!! La persona ya existe @ci: " + data.getCi());
        }
        return res;
    }

    public boolean editar(int ci, Persona data) {
        boolean res = false;
        if (contiene(ci)) {
            NodoD<Persona> nodo = this.conjunto.getP();
            while (nodo != null) {
                if (nodo.getDato().getCi() == ci) {
                    nodo.setDato(data);
                    guardarDatos();
                    res = true;
                    break;
                }
                nodo = nodo.getSig();
            }
        } else {
            System.out.printf("!! La persona no existe @ci: " + ci);
        }
        return res;
    }

    public Persona borrar(int ci) {
        Persona res = null;
        NodoD<Persona> nodo = this.conjunto.getP();
        int index = 0;
        while (nodo != null) {
            if (nodo.getDato().getCi() == ci) {
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

    public Persona getPersona(int ci) {
        Persona res = null;
        NodoD<Persona> nodo = this.conjunto.getP();
        while (nodo != null) {
            if (nodo.getDato().getCi() == ci) {
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
        for (Persona ele : this.conjunto) {
            ele.mostrar();
        }
        System.out.println("?? " + nroEle() + " Elmentos listados");
    }

    public void listar(Predicate<? super Persona> filtro) {
        LDNormal<Persona> listFiltred = this.conjunto.filter(filtro);
        System.out.printf("Elementos filtrados del archivo " + filtro.toString());
        System.out.println();
        for (Persona ele : listFiltred) {
            ele.mostrar();
        }
        System.out.println("?? " + listFiltred.nroEle() + " Elmentos listados");
    }

    public void limpiar(boolean confirm1, boolean confirm2) {
        if (confirm1 && confirm2) {
            this.conjunto = new LDNormal<>();
            guardarDatos();
        }
    }
}
