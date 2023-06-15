package com.example.proyectolab131;

import com.example.proyectolab131.persistence.ArchPersona;
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
        // Cambiar icono y titulo de la ventana
        Image icon = new Image(getClass().getResourceAsStream("/static/icon.png"));
        stage.getIcons().add(icon);
        stage.setTitle("Sistema de desastres naturales");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}