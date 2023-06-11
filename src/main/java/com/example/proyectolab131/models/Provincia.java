package com.example.proyectolab131.models;

public class Provincia {
    private int id;
    private String nombre;
    private String descripcion;
    // ? Imagen de la provincia en el Mapa
    private String ubImg;
    // ? Imagen de la provincia
    private String img;

    public Provincia(int id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Provincia(int id, String nombre, String descripcion, String ubImg, String image) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ubImg = ubImg;
        img = image;
    }

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
                ID: %s \t Nombre: %s
                """, id, nombre);
    }
}
