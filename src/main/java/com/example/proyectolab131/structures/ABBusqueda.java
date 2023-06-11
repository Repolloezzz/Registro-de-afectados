package com.example.proyectolab131.structures;

public abstract class ABBusqueda<T> {
    protected NodoD<T> r;
    protected int nroEle;

    public ABBusqueda() {
        r = null;
        nroEle = 0;
    }

    public boolean esVacio() {
        return r == null;
    }

    public int nroEle() {
        return nroEle;
    }

    protected void listPreOrden(LDNormal<T> list, NodoD<T> raiz) {
        if (raiz != null) {
            list.agregarFin(raiz.getDato());
            listPreOrden(list, raiz.getAnt());
            listPreOrden(list, raiz.getSig());
        }
    }

    protected void listEnOrden(LDNormal<T> list, NodoD<T> raiz) {
        if (raiz != null) {
            listEnOrden(list, raiz.getAnt());
            list.agregarFin(raiz.getDato());
            listEnOrden(list, raiz.getSig());
        }
    }

    protected void listPosOrden(LDNormal<T> list, NodoD<T> raiz) {
        if (raiz != null) {
            listPosOrden(list, raiz.getAnt());
            listPosOrden(list, raiz.getSig());
            list.agregarFin(raiz.getDato());
        }
    }

    protected LDNormal<T> listNivel() {
        LDNormal<T> listResponse = new LDNormal<>();
        Pila<NodoD<T>> raices = new Pila<>();
        Pila<NodoD<T>> ramas = new Pila<>();
        if (!esVacio()) {
            raices.encolar(r);
        } else {
            System.out.println("El arbol esta vacio");
        }
        while (!raices.esVacio()) {
            while (!raices.esVacio()) {
                NodoD<T> elementoRaiz = raices.sacar();
                listResponse.agregarFin(elementoRaiz.getDato());
                NodoD<T> ramaIzq = elementoRaiz.getAnt();
                NodoD<T> ramaDer = elementoRaiz.getSig();
                if (ramaIzq != null) {
                    ramas.encolar(ramaIzq);
                }
                if (ramaDer != null) {
                    ramas.encolar(ramaDer);
                }
            }
            raices.vaciar(ramas);
        }
        return listResponse;
    }

    abstract void agregar(T dato);

    abstract T buscar(int id);

    abstract T eliminar(int id);
}
