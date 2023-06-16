package com.example.proyectolab131;

import com.example.proyectolab131.models.Familia;
import com.example.proyectolab131.models.Persona;
import com.example.proyectolab131.models.RegPersonas;
import com.example.proyectolab131.structures.LDNormal;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

// Clase para ejecutar JAVAFX
public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        // Cambiar icono y título de la ventana
        // Imagenes en el paquete src/main/resources
        Image icon = new Image("/static/icon.png");
        stage.getIcons().add(icon);
        stage.setTitle("Sistema de desastres naturales");
        stage.setScene(scene);

        // Pruebas, Por defecto utiliza los archivos en /data
        RegPersonas reg = new RegPersonas();

        // Listar todas las personas de sexo masculino; TRUE = HOMBRE
        // Además deben de ser mayores de 60 años
        reg.listarPersona(persona -> persona.isGenero() && persona.getEdad() > 60);

        // Obtener una persona del registro
        System.out.println(">> Mostrando una persona cualquiera");
        Persona alguien = reg.getPersona(98509695);
        alguien.mostrar();
        // Obtener su familia y mostrarlo
        System.out.println(">> Mostrar familia de alguien");
        Familia famAlguien = reg.getFamilia(alguien.getFamiliaId());
        LDNormal<Integer> miembrosCI = famAlguien.getMiembrosCI();
        for (Integer ci : miembrosCI) {
            reg.getPersona(ci).mostrar();
        }

    }

    public static void main(String[] args) {
        launch();
    }
}