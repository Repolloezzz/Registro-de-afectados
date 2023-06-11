package com.example.proyectolab131.structures;

public class NodoD<T> {
    protected T dato;
    protected NodoD<T> ant;
    protected NodoD<T> sig;

    public NodoD() {
        dato = null;
        ant = sig = null;
    }

    public NodoD(T dato) {
        this.dato = dato;
        ant = sig = null;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public NodoD<T> getAnt() {
        return ant;
    }

    public void setAnt(NodoD<T> ant) {
        this.ant = ant;
    }

    public NodoD<T> getSig() {
        return sig;
    }

    public void setSig(NodoD<T> sig) {
        this.sig = sig;
    }
}
