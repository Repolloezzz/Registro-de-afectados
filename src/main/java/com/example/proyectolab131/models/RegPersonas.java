package com.example.proyectolab131.models;

import com.example.proyectolab131.others.TipoMFamilia;
import com.example.proyectolab131.persistence.ArchFamilia;
import com.example.proyectolab131.persistence.ArchPersona;
import com.example.proyectolab131.structures.LDNormal;

import java.util.List;
import java.util.function.Predicate;

public class RegPersonas {
    private String tag;
    private ArchPersona archPer;
    private ArchFamilia archFam;

    public RegPersonas() {
        this.tag = "Registro de personas del departamento de La Paz - Bolivia";
        this.archPer = new ArchPersona();
        this.archFam = new ArchFamilia();
    }

    public RegPersonas(String tag) {
        this.tag = tag;
        this.archPer = new ArchPersona();
        this.archFam = new ArchFamilia();
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public ArchPersona getArchPer() {
        return archPer;
    }

    public ArchFamilia getArchFam() {
        return archFam;
    }

    public int nroPersonas() {
        return archPer.nroEle();
    }

    public int nroPersonas(Predicate<? super Persona> filtro) {
        return archPer.nroEle(filtro);
    }

    public int nroFamilias() {
        return archFam.nroEle();
    }

    public int nroFamilias(Predicate<? super Familia> filtro) {
        return archFam.nroEle(filtro);
    }

    public LDNormal<Persona> listPersonas() {
        return archPer.getConjunto();
    }

    public LDNormal<Persona> listPersonas(Predicate<? super Persona> filtro) {
        return archPer.getConjunto(filtro);
    }

    public LDNormal<Familia> listFamilias() {
        return archFam.getConjunto();
    }

    public LDNormal<Familia> listFamilias(Predicate<? super Familia> filtro) {
        return archFam.getConjunto(filtro);
    }

    public boolean agregarPersona(Persona data) {
        boolean res = false;
        boolean existPerson = archPer.contiene(data.getCi());
        // Agregar la persona solo si no existe
        if (!existPerson) {
            res = archPer.agregar(data);
            // Agregar a la familia o crearsela
            int familiaId = data.getFamiliaId();
            if (familiaId != -1) {
                boolean existFamilia = archFam.contiene(familiaId);
                if (existFamilia) {
                    archFam.agregarMiembro(familiaId, data.getCi(), TipoMFamilia.Indefinido);
                } else {
                    archFam.agregar(new Familia(familiaId, data.getCi(), TipoMFamilia.Indefinido));
                }
            }
        } else {
            System.out.println("++ La persona ya existe @ci: " + data.getCi());
        }
        return res;
    }

    public boolean agregarFamilia(Familia data) {
        boolean res = false;
        boolean existFamilia = archFam.contiene(data.getFamiliaId());
        if (!existFamilia && data.getFamiliaId() != -1) {
            LDNormal<Integer> miembrosCi = data.getMiembrosCI();
            LDNormal<Integer> invalidCi = new LDNormal<>();
            boolean isValid = true;
            // Validar que los miembros son persistentes
            for (Integer ele : miembrosCi) {
                if (!archPer.contiene(ele)) {
                    isValid = false;
                    invalidCi.agregarFin(ele);
                }
            }
            if (isValid) {
                res = archFam.agregar(data);
                // Cambiar las familias, solo si la familia es valida
                for (Integer ele : miembrosCi) {
                    Persona miembro = archPer.getPersona(ele);
                    miembro.setFamiliaId(data.getFamiliaId());
                }
                // Guardar cambios en las personas
                archPer.guardarDatos();
            } else {
                System.out.println("++ La familia contiene miembros NO persistentes");
                for (Integer ele : invalidCi) {
                    System.out.println(ele);
                }
                System.out.println("++ Elimine los miembros no persistentes o agréguelos e intente de nuevo");
            }
        } else {
            System.out.println("++ La familia ya existe o no es valida @familiaId: " + data.getFamiliaId());
        }
        return res;
    }

    public boolean editarPersona(int ci, Persona data) {
        boolean res = false;
        if (archPer.contiene(ci)) {
            int oldFamiliaId = archPer.getPersona(ci).getFamiliaId();
            int newFamiliaId = data.getFamiliaId();
            res = archPer.editar(ci, data);
            if (oldFamiliaId != newFamiliaId) {
                archFam.removerMiembro(oldFamiliaId, ci);
                archFam.agregarMiembro(newFamiliaId, ci, TipoMFamilia.Indefinido);
            }
        } else {
            System.out.println("++ La persona no existe @ci: " + data.getCi());
        }
        return res;
    }

    public boolean editarFamilia(int familiaId, Familia data) {
        boolean res = false;
        if (archFam.contiene(familiaId)) {
            // Validar que los miembros son persistentes
            LDNormal<Integer> oldMiembros = archFam.getFamilia(familiaId).getMiembrosCI();
            LDNormal<Integer> newMiembros = data.getMiembrosCI();
            LDNormal<Integer> invalidCi = new LDNormal<>();
            boolean isValid = true;
            for (Integer ele : newMiembros) {
                if (!archPer.contiene(ele)) {
                    isValid = false;
                    invalidCi.agregarFin(ele);
                }
            }
            if (isValid) {
                res = archFam.editar(familiaId, data);
                // realizar cambios si los miembros son modificados
                if (!oldMiembros.equals(newMiembros)) {
                    for (Integer oldCi : oldMiembros) {
                        Persona ele = archPer.getPersona(oldCi);
                        ele.setFamiliaId(-1);
                        archPer.guardarDatos();
                    }
                    for (Integer newCi : newMiembros) {
                        Persona ele = archPer.getPersona(newCi);
                        ele.setFamiliaId(data.getFamiliaId());
                        archPer.guardarDatos();
                    }
                }
            } else {
                System.out.println("++ La familia contiene miembros NO persistentes");
                for (Integer ele : invalidCi) {
                    System.out.println(ele);
                }
                System.out.println("++ Elimine los miembros no persistentes o agréguelos e intente de nuevo");
            }
        } else {
            System.out.println("++ La familia no existe @familiaId: " + data.getFamiliaId());
        }
        return res;
    }

    public Persona borrarPersona(int ci) {
        Persona res = null;
        if (archPer.contiene(ci)) {
            res = archPer.borrar(ci);
            archFam.removerMiembro(res.getFamiliaId(), ci);
        } else {
            System.out.println("++ La persona no existe @ci: " + ci);
        }
        return res;
    }

    public Familia borrarFamilia(int familiaId) {
        Familia res = null;
        if (archFam.contiene(familiaId)) {
            res = archFam.borrar(familiaId);
            // Borrar a los miembros de la persistencia
            LDNormal<Integer> miembros = res.getMiembrosCI();
            for (Integer ci : miembros) {
                Persona ele = archPer.getPersona(ci);
                ele.setFamiliaId(-1);
                archPer.guardarDatos();
            }
        } else {
            System.out.println("++ La familia no existe @familiaId: " + familiaId);
        }
        return res;
    }

    public Persona getPersona(int ci) {
        return archPer.getPersona(ci);
    }

    public Familia getFamilia(int familiaId) {
        return archFam.getFamilia(familiaId);
    }

    public void listarPersona() {
        archPer.listar();
    }

    public void listarPersona(Predicate<? super Persona> filtro) {
        archPer.listar(filtro);
    }

    public void listarFamilias() {
        archFam.listar();
    }

    public void listarFamilias(Predicate<? super Familia> filtro) {
        archFam.listar(filtro);
    }

    public void limpiarPersonas(boolean confirm1, boolean confirm2) {
        if (confirm1 && confirm1) {
            archPer.limpiar(confirm1, confirm2);
        }
    }

    public void limpiarFamilias(boolean confirm1, boolean confirm2) {
        if (confirm1 && confirm2) {
            archFam.limpiar(confirm1, confirm2);
        }
    }
}
