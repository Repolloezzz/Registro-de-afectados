package com.example.proyectolab131;

import com.example.proyectolab131.models.Familia;
import com.example.proyectolab131.persistence.ArchFamilia;
import com.example.proyectolab131.persistence.ArchPersona;

class HelloApplication {
    public static void main(String[] args) {
        ArchFamilia arch = new ArchFamilia();
        ArchPersona arch1 = new ArchPersona();
        arch.agregarUno(new Familia(arch1.getPersona(94035210), 312));
    }
}
/*

import com.example.proyectolab131.persistence.ArchPersona;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

// Clase para ejecutar JAVAFX
public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        // Main
        ArchPersona arch = new ArchPersona();
        arch.getAll();
    }

    public static void main(String[] args) {
        launch();
    }
}
*/