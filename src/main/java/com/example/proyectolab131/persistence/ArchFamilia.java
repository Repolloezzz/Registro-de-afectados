package com.example.proyectolab131.persistence;

import com.example.proyectolab131.enums.TipoMFamilia;
import com.example.proyectolab131.models.Familia;
import com.example.proyectolab131.models.Persona;
import com.example.proyectolab131.structures.LDNormal;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

// Dependencias de Java para leer archivos
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ArchFamilia {
    private final String filePath;
    private LDNormal<Familia> conjunto;

    public ArchFamilia() {
        filePath = "data/familias.json";
        cargarDatos();
    }

    public ArchFamilia(String path) {
        filePath = path;
        cargarDatos();
    }

    private void cargarDatos() {
        Gson gson = new Gson();
        try (Reader reader = new FileReader(filePath)) {
            Type typeList = new TypeToken<List<Familia>>() {
            }.getType();
            List<Familia> listConverter = gson.fromJson(reader, typeList);
            LDNormal<Familia> listResponse = new LDNormal<>();
            listConverter.forEach(listResponse::agregarFin);
            conjunto = listResponse;
        } catch (IOException e) {
            conjunto = new LDNormal<>();
        }
    }

    private void setTodo(List<Familia> newData) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Type listType = new TypeToken<List<Familia>>() {
        }.getType();
        String json = gson.toJson(newData.stream().filter(ele -> ele.nroEle() != 0).collect(Collectors.toList()), listType);
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(json);
            System.out.println("Archivo editado correctamente");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void setTodo(LDNormal<Familia> newData) {
        List<Familia> list = newData.ldnormalToList();
        setTodo(list);
    }

    public LDNormal<Familia> getConjunto() {
        return conjunto;
    }

    public LDNormal<Familia> getConjunto(Predicate<? super Familia> condicion) {
        return conjunto.filter(condicion);
    }


    public int nroEle() {
        return conjunto.nroEle();
    }

    public int nroEle(Predicate<? super Familia> condicion) {
        return getConjunto(condicion).nroEle();
    }

    public void agregar(Familia data) {
        if (getFamilia(data.getFamiliaId()) == null) {
            conjunto.agregarFin(data);
        } else {
            System.out.println("La familia ya existe, mejor editela (ID)");
        }
        setTodo(conjunto);
    }

    public void editar(int id, Familia newData) {
        LDNormal<Familia> list = conjunto;
        for (int i = 0; i < list.nroEle(); i++) {
            if (list.getK(i).getFamiliaId() == id) {
                list.setK(i, newData);
                break;
            }
        }
        setTodo(list);
    }

    public void editar(Familia newData) {
        editar(newData.getFamiliaId(), newData);
    }

    public void borrar(int familiaId, ArchPersona arch) {
        for (int i = 0; i < conjunto.nroEle(); i++) {
            Familia ele = conjunto.getK(i);
            if (ele.getFamiliaId() == familiaId) {
                LDNormal<Integer> miembros = ele.getListMiembrosCi();
                for (int j = 0; j < miembros.nroEle(); j++) {
                    arch.cambiarFamilia(miembros.getK(i), familiaId, -1, this);
                }
                conjunto.removerK(i);
                break;
            }
        }
        setTodo(conjunto);
    }

    public void listar() {
        System.out.println(">> Listando las personas en el archivo PERSONAS.JSON");
        System.out.println();
        for (Familia ele : conjunto) {
            ele.mostrar();
        }
        System.out.println(">>> " + conjunto.nroEle() + " elementos listados");
    }

    public void listar(Predicate<? super Familia> condicion) {
        LDNormal<Familia> listFiltered = conjunto.filter(condicion);
        System.out.printf(">> Datos del archivo persistente de PERSONA");
        System.out.println();
        for (Familia ele : listFiltered) {
            ele.mostrar();
        }
        System.out.println(">>> " + listFiltered.nroEle() + " elementos listados");
    }

    public Familia getFamilia(int familiaId) {
        Familia res = null;
        for (int i = 0; i < nroEle(); i++) {
            Familia ele = conjunto.getK(i);
            if (ele.getFamiliaId() == familiaId) {
                res = ele;
                break;
            }
        }
        return res;
    }

    public void agregarMiembro(int miembroCi, TipoMFamilia tipo, int familiaId, ArchPersona arch) {
        Familia familia = getFamilia(familiaId);
        if (familia != null) {
            if (arch.getPersona(miembroCi) != null) {
                familia.agregarFin(miembroCi, tipo);
            } else {
                System.out.println("No existe la persona con @ci: " + miembroCi);
            }
        } else {
            System.out.println("No existe una familia con @familiaId: " + familiaId);
        }
        setTodo(conjunto);
    }

    public void agregarMiembro(int miembroCi, int familiaId, ArchPersona arch) {
        agregarMiembro(miembroCi, TipoMFamilia.Indefinido, familiaId, arch);
    }

    public void agregarMiembro(Persona miembro, int familiaId, ArchPersona arch) {
        Familia familia = getFamilia(familiaId);
        if (familia != null) {
            if (arch.getPersona(miembro.getCi()) != null) {
                arch.agregar(miembro, this);
                arch.cambiarFamilia(miembro.getCi(), -1, familiaId, this);
            } else {
                arch.cambiarFamilia(miembro.getCi(), miembro.getFamiliaId(), familiaId, this);
            }
        } else {
            System.out.println("No existe una familia con @familiaId: " + familiaId);
        }
        setTodo(conjunto);
    }

    public void removeMiembro(int miembroCi, int familiaId, ArchPersona arch) {
        Familia familia = getFamilia(familiaId);
        if (familia != null) {
            familia.removeMiembro(miembroCi);
            arch.cambiarFamilia(miembroCi, -1, -1, this);
        } else {
            System.out.println(">> ! No existe ninguna familia con @familiaId: " + familiaId);
        }
        setTodo(conjunto);
    }

}
