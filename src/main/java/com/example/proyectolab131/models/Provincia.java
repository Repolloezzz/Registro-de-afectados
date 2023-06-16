package com.example.proyectolab131.models;

import java.io.Serializable;

public class Provincia implements Serializable {
    private int id;
    private String nombre;
    private String descripcion;
    private String ubImg;
    private String img;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUbImg() {
        return ubImg;
    }

    public void setUbImg(String ubImg) {
        this.ubImg = ubImg;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void mostrar() {
        System.out.printf("""
                @id: %s
                Nombre: %s 
                """, id, nombre);
    }
}
