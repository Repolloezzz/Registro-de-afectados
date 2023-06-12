package com.example.proyectolab131.persistence;

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

public class ArchPersona {
    private final String filePath;

    public ArchPersona() {
        filePath = "data/personas.json";
    }

    public ArchPersona(String path) {
        filePath = path;
    }

    public LDNormal<Persona> getTodo() {
        Gson gson = new Gson();
        try (Reader reader = new FileReader(filePath)) {
            Type typeList = new TypeToken<List<Persona>>() {
            }.getType();
            List<Persona> listConverter = gson.fromJson(reader, typeList);
            LDNormal<Persona> listResponse = new LDNormal<>();
            listConverter.forEach(listResponse::agregarFin);
            return listResponse;
        } catch (IOException e) {
            return null;
        }
    }

    public void setTodo(List<Persona> newData) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Type listType = new TypeToken<List<Persona>>() {
        }.getType();
        String json = gson.toJson(newData, listType);
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(json);
            System.out.println("Archivo editado correctamente");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setTodo(LDNormal<Persona> newData) {
        List<Persona> list = newData.ldnormalToList();
        setTodo(list);
    }

    public void agregarUno(Persona dato) {
        LDNormal<Persona> list = getTodo();
        list.agregarFin(dato);
        setTodo(list);
    }

    public void editarUno(int ci, Persona newData) {
        LDNormal<Persona> list = getTodo();
        for (int i = 0; i < list.nroEle(); i++) {
            if (list.getK(i).getCi() == ci) {
                list.setK(i, newData);
            }
        }
        setTodo(list);
    }

    public void editarUno(Persona newData) {
        editarUno(newData.getCi(), newData);
    }

    public void borrarUno(int ci) {
        LDNormal<Persona> list = getTodo();
        for (int i = 0; i < list.nroEle(); i++) {
            Persona ele = list.getK(i);
            if (ele.getCi() == ci) {
                ArchFamilia arch = new ArchFamilia();
                Familia fam = arch.getFamilia(ele.getFamiliaId());
                if (fam != null) {
                    fam.eliminarMiembro(ele.getCi());
                    arch.editarUno(fam);
                }
                list.removerK(i);
                break;
            }
        }
        setTodo(list);
    }

    public void borrarUno(Persona data) {
        borrarUno(data.getCi());
    }

    public void listarTodo() {
        LDNormal<Persona> list = getTodo();
        System.out.println(">> Listando las personas en el archivo PERSONAS.JSON");
        for (Persona ele : list) {
            System.out.println(ele.getNombre());
        }
    }

    public Persona getPersona(int ci) {
        LDNormal<Persona> list = getTodo();
        for (Persona ele : list) {
            if (ele.getCi() == ci) {
                return ele;
            }
        }
        return null;
    }

    public Persona getPersona(String nombre) {
        LDNormal<Persona> list = getTodo();
        for (Persona ele : list) {
            if (ele.getNombre().equals(nombre)) {
                return ele;
            }
        }
        return null;
    }

    public void listar() {
        LDNormal<Persona> list = getTodo();
        System.out.printf(">> Datos del archivo persistente de PERSONA");
        for (Persona ele : list) {
            ele.mostrar();
        }
    }
}