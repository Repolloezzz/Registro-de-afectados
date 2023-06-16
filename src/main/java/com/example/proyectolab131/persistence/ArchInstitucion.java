package com.example.proyectolab131.persistence;

import com.example.proyectolab131.models.Institucion;
import com.example.proyectolab131.structures.LDNormal;
import com.example.proyectolab131.structures.NodoD;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ArchInstitucion {
    // 📁 directorio del archivo de persistencia
    private String filePath;
    // 📚 elementos persistentes del archivo
    private LDNormal<Institucion> conjunto;

    public ArchInstitucion() {
        this.filePath = "data/instituciones.obj";
        this.conjunto = new LDNormal<>();
        cargarDatos();
    }

    public ArchInstitucion(String filePath) {
        this.filePath = filePath;
        this.conjunto = new LDNormal<>();
        cargarDatos();
    }

    public void cargarDatos() {
        try {
            FileInputStream fileIn = new FileInputStream(filePath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            List<Institucion> listRes = (List<Institucion>) objectIn.readObject();
            objectIn.close();
            fileIn.close();
            for (Institucion ele : listRes) {
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
            List<Institucion> listOut = new ArrayList<>();
            for (Institucion ele : conjunto) {
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

    public LDNormal<Institucion> getConjunto() {
        return this.conjunto;
    }

    public LDNormal<Institucion> getConjunto(Predicate<? super Institucion> filtro) {
        return this.conjunto.filter(filtro);
    }

    public int nroEle() {
        return this.conjunto.nroEle();
    }

    public int nroEle(Predicate<? super Institucion> filtro) {
        return this.conjunto.filter(filtro).nroEle();
    }

    public boolean contiene(int id) {
        return this.conjunto.contiene(ele -> ele.getId() == id);
    }

    public boolean agregar(Institucion data) {
        boolean res = false;
        if (!contiene(data.getId())) {
            this.conjunto.agregarFin(data);
            guardarDatos();
            res = true;
        } else {
            System.out.printf("!! La Institucin ya existe @Nombre: " + data.getNombre());
        }
        return res;
    }

    public boolean editar(int id, Institucion data) {
        boolean res = false;
        if (contiene(id)) {
            NodoD<Institucion> nodo = this.conjunto.getP();
            while (nodo != null) {
                if (nodo.getDato().getId() == id) {
                    nodo.setDato(data);
                    guardarDatos();
                    res = true;
                    break;
                }
                nodo = nodo.getSig();
            }
        } else {
            System.out.printf("!! La Institucion no existe @id: " + id);
        }
        return res;
    }

    public Institucion borrar(int id) {
        Institucion res = null;
        NodoD<Institucion> nodo = this.conjunto.getP();
        int index = 0;
        while (nodo != null) {
            if (nodo.getDato().getId() == id) {
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

    public Institucion getInstitucion(int id) {
        Institucion res = null;
        NodoD<Institucion> nodo = this.conjunto.getP();
        while (nodo != null) {
            if (nodo.getDato().getId() == id) {
                res = nodo.getDato();
                break;
            }
            nodo = nodo.getSig();
        }
        return res;
    }

    public void listar() {
        System.out.printf("Elementos del archivo Instituciones");
        System.out.println();
        for (Institucion ele : this.conjunto) {
            ele.mostrar();
        }
        System.out.println("?? " + nroEle() + " Elementos listados");
    }

    public void listar(Predicate<? super Institucion> filtro) {
        LDNormal<Institucion> listFiltered = this.conjunto.filter(filtro);
        System.out.printf("Elementos filtrados del archivo Instituciones: " + filtro.toString());
        System.out.println();
        for (Institucion ele : listFiltered) {
            ele.mostrar();
        }
        System.out.println("?? " + listFiltered.nroEle() + " Elmentos listados");
    }

    public void limpiar(boolean confirm1, boolean confirm2) {
        if (confirm1 && confirm2) {
            this.conjunto = new LDNormal<>();
            guardarDatos();
        }
    }
}
