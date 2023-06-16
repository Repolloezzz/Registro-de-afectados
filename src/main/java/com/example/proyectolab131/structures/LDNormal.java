package com.example.proyectolab131.structures;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class LDNormal<T> implements Iterable<T>, Serializable {
    protected NodoD<T> p;
    protected int nroEle;

    public LDNormal() {
        p = null;
        nroEle = 0;
    }

    public boolean esVacio() {
        return p == null;
    }

    public int nroEle() {
        return nroEle;
    }

    public int nroEle(Predicate<? super T> filtro) {
        return this.filter(filtro).nroEle();
    }

    public NodoD<T> getP() {
        return p;
    }

    public void setP(NodoD<T> q) {
        p = q;
        int con = 0;
        NodoD<T> aux = p;
        while (aux != null) {
            aux = aux.getSig();
            con++;
        }
        nroEle = con;
    }

    public void agregarFin(T dato) {
        NodoD<T> newNodo = new NodoD<>(dato);
        if (esVacio()) {
            p = newNodo;
        } else {
            NodoD<T> auxNodo = p;
            while (auxNodo.getSig() != null) {
                auxNodo = auxNodo.getSig();
            }
            auxNodo.setSig(newNodo);
            newNodo.setAnt(auxNodo);
        }
        nroEle++;
    }

    public void agregarInicio(T dato) {
        NodoD<T> newNodo = new NodoD<>(dato);
        if (esVacio()) {
            p = newNodo;
        } else {
            newNodo.setSig(p);
            p.setAnt(newNodo);
            p = newNodo;
        }
        nroEle++;
    }

    public void insertarK(T dato, int index) {
        if (index == 0) agregarInicio(dato);
        else if (index == nroEle - 1) agregarFin(dato);
        else if (index >= 0 && index < nroEle) {
            NodoD<T> auxNodo = p;
            NodoD<T> newNodo = new NodoD<>(dato);
            for (int i = 0; i < index; i++) {
                auxNodo = auxNodo.getSig();
            }
            NodoD<T> antAuxNodo = auxNodo.getAnt();
            antAuxNodo.setSig(newNodo);
            newNodo.setAnt(antAuxNodo);
            newNodo.setSig(auxNodo);
            nroEle++;
        } else {
            System.out.println("El index esta fuera de rango");
        }
    }

    public T eliminarFin() {
        T res = null;
        if (!esVacio()) {
            NodoD<T> auxNodo = p;
            while (auxNodo.getSig() != null) {
                auxNodo = auxNodo.getSig();
            }
            res = auxNodo.getDato();
            NodoD<T> antAuxNodo = auxNodo.getAnt();
            antAuxNodo.setSig(null);
            nroEle--;
        }
        return res;
    }

    public T eliminarInicio() {
        T res = null;
        if (!esVacio()) {
            res = p.getDato();
            p = p.getSig();
            if (p != null) p.setAnt(null);
        }
        return res;
    }

    public T removerK(int index) {
        if (index == 0) return eliminarInicio();
        else if (index == nroEle - 1) return eliminarFin();
        else if (index >= 0 && index < nroEle) {
            NodoD<T> auxNodo = p;
            for (int i = 0; i < index; i++) {
                auxNodo = auxNodo.getSig();
            }
            T res = auxNodo.getDato();
            NodoD<T> antAuxNodo = auxNodo.getAnt();
            NodoD<T> sigAuxNodo = auxNodo.getSig();
            antAuxNodo.setSig(sigAuxNodo);
            sigAuxNodo.setAnt(antAuxNodo);
            return res;
        } else {
            System.out.println("El index esta fuera de rango");
            return null;
        }
    }

    public boolean contiene(T dato) {
        NodoD<T> aux = p;
        while (aux != null) {
            if (aux.getDato().equals(dato)) {
                return true;
            }
            aux = aux.getSig();
        }
        return false;
    }

    public boolean contiene(Predicate<? super T> condicion) {
        NodoD<T> aux = p;
        while (aux != null) {
            if (condicion.test(aux.getDato())) {
                return true;
            }
            aux = aux.getSig();
        }
        return false;
    }

    public T getK(int indexK) {
        NodoD<T> auxNodo = p;
        for (int i = 0; i < indexK; i++) {
            auxNodo = auxNodo.getSig();
        }
        return (auxNodo == null) ? null : auxNodo.getDato();
    }

    public void setK(int indexK, T newData) {
        NodoD<T> auxNodo = p;
        for (int i = 0; i < indexK; i++) {
            auxNodo = auxNodo.getSig();
        }
        auxNodo.setDato(newData);
    }

    public void mostrar() {
        NodoD<T> auxNodo = p;
        while (auxNodo != null) {
            System.out.println(auxNodo.getDato());
            auxNodo = auxNodo.getSig();
        }
    }

    // 🏹 convertir lista normal a lista JAVA
    public List<T> ldnormalToList() {
        ArrayList<T> list = new ArrayList<>();
        NodoD<T> auxNodo = p;
        while (auxNodo != null) {
            list.add(auxNodo.getDato());
            auxNodo = auxNodo.getSig();
        }
        return list;
    }

    // ⭐ Para integrar el iterador FOREACH
    @Override
    public Iterator<T> iterator() {
        return new LDNormalIterator();
    }

    private class LDNormalIterator implements Iterator<T> {
        private NodoD<T> current = p;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T dato = current.getDato();
            current = current.getSig();
            return dato;
        }
    }

    public LDNormal<T> filter(Predicate<? super T> condicion) {
        LDNormal<T> listReponse = new LDNormal<>();
        for (T ele : this) {
            if (condicion.test(ele)) {
                listReponse.agregarFin(ele);
            }
        }
        return listReponse;
    }
}
