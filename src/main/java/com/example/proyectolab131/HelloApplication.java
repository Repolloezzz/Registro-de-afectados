package com.example.proyectolab131;

import com.example.proyectolab131.models.Persona;
import com.example.proyectolab131.persistence.ArchPersona;
import com.example.proyectolab131.structures.ABBPersona;
import com.example.proyectolab131.structures.LDNormal;

class HelloApplication {
    public static void main(String[] args) {
        ArchPersona arch = new ArchPersona();
        LDNormal<Persona> list = arch.getAllData();
        ABBPersona arb = new ABBPersona();
        for (int i = 0; i < 5; i++) {
            arb.agregar(list.getK(i));
        }
        LDNormal<Persona> ord = arb.listEnOrden();
        for (Persona ele : ord) {
            System.out.println(ele.getNombre());
        }
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