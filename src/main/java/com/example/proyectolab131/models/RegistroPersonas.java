package com.example.proyectolab131.models;

import com.example.proyectolab131.enums.TipoMFamilia;
import com.example.proyectolab131.persistence.ArchFamilia;
import com.example.proyectolab131.persistence.ArchPersona;
import com.example.proyectolab131.structures.LDNormal;

import java.util.function.Predicate;

public class RegistroPersonas {
    private String tag;
    private ArchPersona personasData;
    private ArchFamilia familiasData;

    public RegistroPersonas() {
        tag = "Registro de personas en el departamento de La Paz - Bolivia";
        personasData = new ArchPersona();
        familiasData = new ArchFamilia();
    }

    public RegistroPersonas(String tag) {
        this.tag = tag;
        personasData = new ArchPersona();
        familiasData = new ArchFamilia();
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public ArchPersona getPersonasData() {
        return personasData;
    }

    public void setPersonasData(ArchPersona personasData) {
        this.personasData = personasData;
    }

    public ArchFamilia getFamiliasData() {
        return familiasData;
    }

    public void setFamiliasData(ArchFamilia familiasData) {
        this.familiasData = familiasData;
    }

    public int nroPersonas() {
        return personasData.nroEle();
    }

    public int nroPersonas(Predicate<? super Persona> filtro) {
        return personasData.nroEle(filtro);
    }

    public int nroFamilias() {
        return personasData.nroEle();
    }

    public int nroFamilias(Predicate<? super Familia> filtro) {
        return familiasData.nroEle();
    }

    public LDNormal<Persona> getlistPersonas() {
        return personasData.getConjunto();
    }

    public LDNormal<Persona> getlistPersona(Predicate<? super Persona> filtro) {
        return personasData.getConjunto(filtro);
    }

    public LDNormal<Familia> getlistFamilias() {
        return familiasData.getConjunto();
    }

    public LDNormal<Familia> getlistFamilias(Predicate<? super Familia> filtro) {
        return familiasData.getConjunto(filtro);
    }

    public void agregarPersona(Persona newPersona) {
        personasData.agregar(newPersona, familiasData);
    }


    public void editarPersona(int ci, Persona newData) {
        personasData.editar(ci, newData, familiasData);
    }

    public void editarPersona(Persona newData) {
        personasData.editar(newData, familiasData);
    }

    public void editarFamilia(int id, Familia newData) {
        familiasData.editar(id, newData);
    }

    public void editarFamilia(Familia newData) {
        familiasData.editar(newData);
    }

    public void borrarPersona(int ci) {
        personasData.borrar(ci, familiasData);
    }

    public void borrarFamilia(int id) {
        familiasData.borrar(id, personasData);
    }

    public void listarPersonas() {
        personasData.listar();
    }

    public void listarPersonas(Predicate<? super Persona> filtro) {
        personasData.listar(filtro);
    }

    public void listarFamilias() {
        familiasData.listar();
    }

    public void listarFamilias(Predicate<? super Familia> filtro) {
        familiasData.listar(filtro);
    }

    public void agregarEnFamilia(int miembroCi, TipoMFamilia tipo, int famaliaId) {
        familiasData.agregarMiembro(miembroCi, tipo, famaliaId, personasData);
    }

    public void agregarEnFamilia(int miembroCi, int familiaId) {
        agregarEnFamilia(miembroCi, TipoMFamilia.Indefinido, familiaId);
    }

    public void eliminarDeFamilia(int miembroCi, int familiaId) {
        familiasData.removeMiembro(miembroCi, familiaId, personasData);
    }

}

