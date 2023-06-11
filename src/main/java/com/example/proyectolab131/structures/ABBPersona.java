package com.example.proyectolab131.structures;

import com.example.proyectolab131.models.Persona;

public class ABBPersona extends ABBusqueda<Persona> {
    public ABBPersona() {
        super();
    }

    public LDNormal<Persona> listEnOrden() {
        LDNormal<Persona> listResponse = new LDNormal<>();
        super.listEnOrden(listResponse, r);
        return listResponse;
    }

    public LDNormal<Persona> listPreOrden() {
        LDNormal<Persona> listResponse = new LDNormal<>();
        super.listPreOrden(listResponse, r);
        return listResponse;
    }

    public LDNormal<Persona> listPosOrden() {
        LDNormal<Persona> listResponse = new LDNormal<>();
        super.listPosOrden(listResponse, r);
        return listResponse;
    }

    @Override

    public void agregar(Persona dato) {
        if (esVacio()) {
            r = new NodoD<>(dato);
        } else {
            r = agregarRecursivo(r, dato);
        }
        nroEle++;
    }

    private NodoD<Persona> agregarRecursivo(NodoD<Persona> nodo, Persona dato) {
        if (nodo == null) nodo = new NodoD<Persona>(dato);
        else if (dato.getCi() > nodo.getDato().getCi()) {
            nodo.setSig(agregarRecursivo(nodo.getSig(), dato));
        } else {
            nodo.setAnt(agregarRecursivo(nodo.getAnt(), dato));
        }
        return nodo;
    }

    @Override
    public Persona buscar(int id) {
        Persona response = null;
        NodoD<Persona> auxNodo = r;
        while (auxNodo != null) {
            Persona ele = r.getDato();
            if (id > ele.getCi()) {
                auxNodo = auxNodo.getSig();
            } else if (id < ele.getCi()) {
                auxNodo = auxNodo.getAnt();
            } else {
                response = auxNodo.getDato();
            }
        }
        return response;
    }

    @Override
    public Persona eliminar(int ci) {
        Persona res = buscar(ci);
        if (res != null) {
            r = eliminarRecursivo(r, ci);
        }
        return res;
    }

    private NodoD<Persona> eliminarRecursivo(NodoD<Persona> nodo, int ci) {
        if (nodo == null) return null;
        if (ci < nodo.getDato().getCi()) {
            nodo.setAnt(eliminarRecursivo(nodo.getAnt(), ci));
        } else if (ci > nodo.getDato().getCi()) {
            nodo.setSig(eliminarRecursivo(nodo.getSig(), ci));
        } else {
            NodoD<Persona> izq = nodo.getAnt();
            NodoD<Persona> der = nodo.getSig();
            if (izq == null && der == null) {
                return null;
            }
            if (izq != null && der == null) {
                return izq;
            }
            if (izq == null && der != null) {
                return der;
            }
            NodoD<Persona> temp = nodoMenor(der);
            nodo.setDato(temp.getDato());
            nodo.setSig(eliminarRecursivo(nodo.getSig(), ci));
        }
        return nodo;
    }

    private NodoD<Persona> nodoMenor(NodoD<Persona> nodo) {
        while (nodo.getAnt() != null) {
            nodo = nodo.getAnt();
        }
        return nodo;
    }
}
