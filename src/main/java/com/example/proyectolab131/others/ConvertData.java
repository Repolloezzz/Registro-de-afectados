package com.example.proyectolab131.others;

import com.example.proyectolab131.models.Familia;
import com.example.proyectolab131.models.Persona;
import com.example.proyectolab131.persistence.ArchFamilia;
import com.example.proyectolab131.persistence.ArchPersona;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class ConvertData {

    public List<Persona> jsonPerson() {
        Gson gson = new Gson();
        try (Reader reader = new FileReader("data/personas.json")) {
            Type typeList = new TypeToken<List<Persona>>() {
            }.getType();
            List<Persona> listConverter = gson.fromJson(reader, typeList);
            return listConverter;
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<Persona>();
        }
    }

    public List<Familia> jsonFamilia() {
        Gson gson = new Gson();
        try (Reader reader = new FileReader("data/familias.json")) {
            Type typeList = new TypeToken<List<Familia>>() {
            }.getType();
            List<Familia> listConverter = gson.fromJson(reader, typeList);
            return listConverter;
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<Familia>();
        }
    }

    public void personToObj() {
        List<Persona> list = jsonPerson();
        ArchPersona arch = new ArchPersona();
        arch.limpiar(true, true);
        for (Persona ele : list) {
            arch.agregar(ele);
        }
    }

    public void familiaToObj() {
        List<Familia> list = jsonFamilia();
        ArchFamilia arch = new ArchFamilia();
        arch.limpiar(true, true);
        for (Familia ele : list) {
            arch.agregar(ele);
        }
    }

}
