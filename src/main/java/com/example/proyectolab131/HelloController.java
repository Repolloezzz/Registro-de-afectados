package com.example.proyectolab131;

import com.example.proyectolab131.models.RegistroPersonas;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Hola mundo en JAVAFX!");
        RegistroPersonas reg = new RegistroPersonas();
        reg.listarPersonas();
    }
}