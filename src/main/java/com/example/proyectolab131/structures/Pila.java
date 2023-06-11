package com.example.proyectolab131.structures;

public class Pila<T> {
    private NodoD<T> inicio;
    private NodoD<T> fin;

    private int nroEle;

    public Pila() {
        inicio = fin = null;
        nroEle = 0;
    }

    public boolean esVacio() {
        return nroEle == 0;
    }

    public int nroEle() {
        return nroEle;
    }

    public void encolar(T dato) {
        NodoD<T> newNodo = new NodoD<>(dato);
        if (esVacio()) {
            inicio = newNodo;
            fin = newNodo;
        } else {
            fin.setSig(newNodo);
            newNodo.setAnt(fin);
            fin = newNodo;
        }
        nroEle++;
    }

    public T sacar() {
        T res = null;
        if (!esVacio()) {
            res = fin.getDato();
            NodoD<T> antFin = fin.getAnt();
            if (antFin == null) {
                inicio = fin = null;
            } else {
                antFin.setSig(null);
                fin = antFin;
            }
            nroEle--;
        }
        return res;
    }

    public void vaciar(Pila<T> otraPila) {
        while (!otraPila.esVacio()) {
            encolar(otraPila.sacar());
        }
    }

    public void mostrar() {
        NodoD<T> auxNodo = fin;
        for (int i = nroEle - 1; i >= 0; i--) {
            System.out.println(auxNodo.getDato());
            auxNodo = auxNodo.getAnt();
        }
    }
}
