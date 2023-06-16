package com.example.proyectolab131.persistence;

import com.example.proyectolab131.models.Provincia;
import com.example.proyectolab131.structures.LDNormal;
import com.example.proyectolab131.structures.NodoD;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ArchProvincia {
    // 📁 directorio del archivo de persistencia
    private String filePath;
    // 📚 elementos persistentes del archivo
    private LDNormal<Provincia> conjunto;

    public ArchProvincia() {
        this.filePath = "data/provincias.obj";
        this.conjunto = new LDNormal<>();
        cargarDatos();
    }

    public ArchProvincia(String filePath) {
        this.filePath = filePath;
        this.conjunto = new LDNormal<>();
        cargarDatos();
    }

    public void cargarDatos() {
        try {
            FileInputStream fileIn = new FileInputStream(filePath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            List<Provincia> listRes = (List<Provincia>) objectIn.readObject();
            objectIn.close();
            fileIn.close();
            for (Provincia ele : listRes) {
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
            List<Provincia> listOut = new ArrayList<>();
            for (Provincia ele : conjunto) {
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

    public LDNormal<Provincia> getConjunto() {
        return this.conjunto;
    }

    public LDNormal<Provincia> getConjunto(Predicate<? super Provincia> filtro) {
        return this.conjunto.filter(filtro);
    }

    public int nroEle() {
        return this.conjunto.nroEle();
    }

    public int nroEle(Predicate<? super Provincia> filtro) {
        return this.conjunto.filter(filtro).nroEle();
    }

    public boolean contiene(int id) {
        return this.conjunto.contiene(ele -> ele.getId() == id);
    }

    public boolean agregar(Provincia data) {
        boolean res = false;
        if (!contiene(data.getId())) {
            this.conjunto.agregarFin(data);
            guardarDatos();
            res = true;
        } else {
            System.out.printf("!! La provincia ya existe @id: " + data.getId());
        }
        return res;
    }

    public boolean editar(int id, Provincia data) {
        boolean res = false;
        if (contiene(id)) {
            NodoD<Provincia> nodo = this.conjunto.getP();
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
            System.out.printf("!! La provincia no existe @id: " + id);
        }
        return res;
    }

    public Provincia borrar(int id) {
        Provincia res = null;
        NodoD<Provincia> nodo = this.conjunto.getP();
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

    public Provincia getProvincia(int id) {
        Provincia res = null;
        NodoD<Provincia> nodo = this.conjunto.getP();
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
        System.out.printf("Elementos del archivo Provincias");
        System.out.println();
        for (Provincia ele : this.conjunto) {
            ele.mostrar();
        }
        System.out.println("?? " + nroEle() + " Elmentos listados");
    }

    public void listar(Predicate<? super Provincia> filtro) {
        LDNormal<Provincia> listFiltred = this.conjunto.filter(filtro);
        System.out.printf("Elementos filtrados del archivo Provincias: " + filtro.toString());
        System.out.println();
        for (Provincia ele : listFiltred) {
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
