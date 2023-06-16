package com.example.proyectolab131.models;

import com.example.proyectolab131.structures.LDNormal;
import com.example.proyectolab131.structures.NodoD;

import java.io.Serializable;
import java.util.function.Predicate;

public class Institucion implements Serializable {
    // Nombre es el atributo UNICO, para identificarlo
    private int id;
    private String nombre;
    private String descripcion;

    private String ubicacion;

    private LDNormal<Personal> personal;

    public Institucion(int id, String nombre, String descripcion, String ubicacion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.personal = new LDNormal<>();
    }

    public Institucion(int id, String nombre, String descripcion, String ubicacion, LDNormal<Personal> personal) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.personal = personal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public LDNormal<Personal> getPersonal() {
        return personal;
    }

    public void setPersonal(LDNormal<Personal> personal) {
        this.personal = personal;
    }

    public boolean contiene(Predicate<? super Personal> condicion) {
        return personal.contiene(condicion);
    }

    public int nroPersonal() {
        return personal.nroEle();
    }

    public int nroPersona(Predicate<? super Personal> filtro) {
        return personal.nroEle(filtro);
    }

    public LDNormal<Personal> listPersonal() {
        return personal;
    }

    public LDNormal<Personal> listPersonal(Predicate<? super Personal> filtro) {
        return personal.filter(filtro);
    }

    public Personal getPersonal(int ci) {
        Personal res = null;
        if (contiene(ele -> ele.getPersonalCi() == ci)) {
            NodoD<Personal> nodo = personal.getP();
            while (nodo != null) {
                if (nodo.getDato().getPersonalCi() == ci) {
                    res = nodo.getDato();
                    break;
                }
                nodo = nodo.getSig();
            }
        } else {
            System.out.println(">> El personal no existe @ci: " + ci);
        }
        return res;
    }

    public boolean agregarPersonal(int ci, String cargo) {
        boolean res = false;
        if (!contiene(ele -> ele.getPersonalCi() == ci)) {
            personal.agregarFin(new Personal(ci, cargo));
        } else {
            System.out.println(">> El personal ya existe @ci: " + ci);
        }
        return res;
    }

    public Personal borrarPersonal(int ci) {
        Personal res = null;
        if (contiene(ele -> ele.getPersonalCi() == ci)) {
            NodoD<Personal> nodo = personal.getP();
            int index = 0;
            while (nodo != null) {
                if (nodo.getDato().getPersonalCi() == ci) {
                    res = nodo.getDato();
                    break;
                }
                nodo = nodo.getSig();
                index++;
            }
            personal.removerK(index);
        } else {
            System.out.println(">> El personal no existe @ci: " + ci);
        }
        return res;
    }

    public boolean editarPersonal(int ci, Personal data) {
        boolean res = false;
        if (contiene(ele -> ele.getPersonalCi() == ci)) {
            NodoD<Personal> nodo = personal.getP();
            while (nodo != null) {
                if (nodo.getDato().getPersonalCi() == ci) {
                    nodo.setDato(data);
                    res = true;
                    break;
                }
                nodo = nodo.getSig();
            }
        } else {
            System.out.println(">> El personal no existe @ci: " + ci);
        }
        return res;
    }

    public void mostrar() {
        System.out.printf("""
                @id: %s
                Nombre: %s
                Ubicacin: %s
                """, id, nombre, ubicacion);
        for (Personal ele : personal) {
            ele.mostrar();
        }
        System.out.printf(">> " + personal.nroEle() + " Elementos listados");
    }
}
