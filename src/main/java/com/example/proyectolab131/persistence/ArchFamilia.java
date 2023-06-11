package com.example.proyectolab131.persistence;

import com.example.proyectolab131.models.Familia;
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

public class ArchFamilia {
    private final String filePath;

    public ArchFamilia() {
        filePath = "data/familias.json";
    }

    public ArchFamilia(String path) {
        filePath = path;
    }

    public LDNormal<Familia> getAllData() {
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

    public void setAllData(List<Familia> newData) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Type listType = new TypeToken<List<Familia>>() {
        }.getType();
        String json = gson.toJson(newData, listType);
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(json);
            System.out.println("Archivo editado correctamente");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAllData(LDNormal<Familia> newData) {
        List<Familia> list = newData.ldnormalToList();
        setAllData(list);
    }

    public void agregar(Familia dato) {
        LDNormal<Familia> list = getAllData();
        list.agregarFin(dato);
        setAllData(list);
    }
}
