package com.example.proyectolab131.persistence;

import com.example.proyectolab131.models.Familia;
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
import java.util.stream.Collectors;

public class ArchFamilia {
    private final String filePath;

    public ArchFamilia() {
        filePath = "data/familias.json";
    }

    public ArchFamilia(String path) {
        filePath = path;
    }

    public LDNormal<Familia> getTodo() {
        Gson gson = new Gson();
        try (Reader reader = new FileReader(filePath)) {
            Type typeList = new TypeToken<List<Familia>>() {
            }.getType();
            List<Familia> listConverter = gson.fromJson(reader, typeList);
            LDNormal<Familia> listResponse = new LDNormal<>();
            listConverter.forEach(listResponse::agregarFin);
            return listResponse;
        } catch (IOException e) {
            return null;
        }
    }

    public void setTodo(List<Familia> newData) {
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

    public void setTodo(LDNormal<Familia> newData) {
        List<Familia> list = newData.ldnormalToList();
        setTodo(list);
    }

    public void agregarUno(Familia dato) {
        LDNormal<Familia> list = getTodo();
        list.agregarFin(dato);
        setTodo(list);
    }

    public void editarUno(int id, Familia newData) {
        LDNormal<Familia> list = getTodo();
        for (int i = 0; i < list.nroEle(); i++) {
            if (list.getK(i).getId() == id) {
                list.setK(i, newData);
            }
        }
        setTodo(list);
    }

    public void editarUno(Familia newData) {
        editarUno(newData.getId(), newData);
    }

    public void borrarUno(int id) {
        LDNormal<Familia> list = getTodo();
        for (int i = 0; i < list.nroEle(); i++) {
            Familia ele = list.getK(i);
            if (ele.getId() == id) {
                ArchPersona arch = new ArchPersona();
                LDNormal<Persona> miembros = ele.getPersonas();
                for (int j = 0; j < miembros.nroEle(); j++) {
                    Persona miembro = miembros.getK(i);
                    miembro.setFamiliaId(-1, true);
                    arch.editarUno(miembro);
                }
                list.removerK(i);
                break;
            }
        }
        setTodo(list);
    }

    public void listarTodo() {
        LDNormal<Familia> list = getTodo();
        System.out.println(">> Listando las personas en el archivo PERSONAS.JSON");
        for (Familia ele : list) {
            System.out.println(ele.getId());
        }
    }

    public Familia getFamilia(int id) {
        LDNormal<Familia> list = getTodo();
        for (Familia ele : list) {
            if (ele.getId() == id) {
                return ele;
            }
        }
        return null;
    }
}
