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

public class ArchPersona {
    private final String filePath;
    private LDNormal<Persona> conjunto;

    public ArchPersona() {
        filePath = "data/personas.json";
        cargarDatos();
    }

    public ArchPersona(String path) {
        filePath = path;
        cargarDatos();
    }

    private void cargarDatos() {
        // Cargar los datos si existe el archivo en filePath
        Gson gson = new Gson();
        try (Reader reader = new FileReader(filePath)) {
            Type typeList = new TypeToken<List<Persona>>() {
            }.getType();
            List<Persona> listConverter = gson.fromJson(reader, typeList);
            LDNormal<Persona> listResponse = new LDNormal<>();
            listConverter.forEach(listResponse::agregarFin);
            conjunto = listResponse;
        } catch (IOException e) {
            conjunto = new LDNormal<Persona>();
        }
    }

    private void setTodo(List<Persona> newData) {
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

    private void setTodo(LDNormal<Persona> newData) {
        List<Persona> list = newData.ldnormalToList();
        setTodo(list);
    }

    public LDNormal<Persona> getConjunto() {
        return conjunto;
    }

    public LDNormal<Persona> getConjunto(Predicate<? super Persona> condicion) {
        return conjunto.filter(condicion);
    }


    public int nroEle() {
        return conjunto.nroEle();
    }

    public int nroEle(Predicate<? super Persona> condicion) {
        return getConjunto(condicion).nroEle();
    }

    public void agregar(Persona dato, ArchFamilia arch) {
        // Validar que no exista otro igual
        if (getPersona(dato.getCi()) == null) {
            conjunto.agregarFin(dato);
            // Guardar los cambios si tiene familia
            cambiarFamilia(dato.getCi(), dato.getFamiliaId(), dato.getFamiliaId(), arch);
        } else {
            System.out.println("La persona ya existe, mejor editela (CI)");
        }
        setTodo(conjunto);
    }

    public void editar(int ci, Persona newData, ArchFamilia arch) {
        for (int i = 0; i < conjunto.nroEle(); i++) {
            Persona ele = conjunto.getK(i);
            if (ele.getCi() == ci) {
                if (!ele.getFamiliaId().equals(newData.getFamiliaId())) {
                    // Cambiar enlaces o romperlos
                    cambiarFamilia(ci, ele.getFamiliaId(), newData.getFamiliaId(), arch);
                }
                conjunto.setK(i, newData);
            }
        }
        setTodo(conjunto);
    }


    public void editar(Persona newData, ArchFamilia arch) {
        editar(newData.getCi(), newData, arch);
    }

    public void borrar(int ci, ArchFamilia arch) {
        for (int i = 0; i < conjunto.nroEle(); i++) {
            Persona ele = conjunto.getK(i);
            if (ele.getCi() == ci) {
                Familia fam = arch.getFamilia(ele.getFamiliaId());
                if (fam != null) {
                    cambiarFamilia(ci, ele.getFamiliaId(), -1, arch);
                }
                conjunto.removerK(i);
                break;
            }
        }
        setTodo(conjunto);
    }

    public void borrar(Persona data, ArchFamilia arch) {
        borrar(data.getCi(), arch);
    }

    public void listar() {
        System.out.printf(">>> Datos del archivo persistente de PERSONA");
        System.out.println();
        for (Persona ele : conjunto) {
            ele.mostrar();
        }
        System.out.println(">>>  " + conjunto.nroEle() + " elementos listados");
    }


    public void listar(Predicate<? super Persona> condicion) {
        LDNormal<Persona> listFiltered = conjunto.filter(condicion);
        System.out.printf(">>> Datos del archivo persistente de PERSONA");
        System.out.println();
        for (Persona ele : listFiltered) {
            ele.mostrar();
        }
        System.out.println(">>> " + listFiltered.nroEle() + " elementos listados");
    }

    public Persona getPersona(int ci) {
        Persona res = null;
        for (int i = 0; i < nroEle(); i++) {
            Persona ele = conjunto.getK(i);
            if (ele.getCi() == ci) {
                res = ele;
                break;
            }
        }
        return res;
    }

    public Persona getPersona(String nombres, String apellidos) {
        Persona res = null;
        for (int i = 0; i < nroEle(); i++) {
            Persona ele = conjunto.getK(i);
            if (ele.getNombres().equals(nombres) && ele.getApellidos().equals(apellidos)) {
                res = ele;
                break;
            }
        }
        return res;
    }


    public void cambiarFamilia(int ci, TipoMFamilia tipo, int oldFamiliaId, int newFamiliaId, ArchFamilia arch) {
        Persona ele = getPersona(ci);
        if (ele != null) {
            // Borrar de la vieja familia
            Familia oldFamilia = arch.getFamilia(oldFamiliaId);
            if (oldFamilia != null) {
                arch.removeMiembro(ci, oldFamiliaId, this);
            }
            // Agregar a la nueva familia o solo eliminar
            ele.setFamiliaId(newFamiliaId);
            // Si familiaId != -1  ==> Tiene familia
            if (newFamiliaId != -1) {
                Familia newFamilia = arch.getFamilia(newFamiliaId);
                if (newFamilia != null) {
                    // Existe familia ==> agregarlo
                    arch.agregarMiembro(ci, tipo, newFamiliaId, this);
                } else {
                    // No existe familia ==> crearlo
                    arch.agregar(new Familia(ele.getCi(), tipo, newFamiliaId));
                }
            }
        }
        setTodo(conjunto);
    }

    public void cambiarFamilia(int miembroCi, int oldFamiliaId, int newFamiliaId, ArchFamilia arch) {
        cambiarFamilia(miembroCi, TipoMFamilia.Indefinido, oldFamiliaId, newFamiliaId, arch);
    }

}