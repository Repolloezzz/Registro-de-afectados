package com.example.proyectolab131.structure;

public class NodoD<T> {
    private T dato;
    private NodoD<T> ant;
    private NodoD<T> sig;

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
